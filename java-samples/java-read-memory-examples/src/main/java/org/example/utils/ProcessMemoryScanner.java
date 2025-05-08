package org.example.utils;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;

import java.util.ArrayList;
import java.util.List;
import com.sun.jna.*;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.platform.win32.WinNT.*;
import com.sun.jna.ptr.IntByReference;
;

/**
 * {@code @class name:} ProcessMemoryScanner
 * {@code @description:} java通过jna调用windows系统api读取内存。
 *
 *  发现 net.java.dev.jna:jna-platform 中提供了一个帮助实现的kernel32类。那岂不是直接使用就可以了？
 *
 *
 * {@code @author:} d
 * {@code @createTime:} 2025/05/03 10:23
 * {@code @Version:} 1.0
 */
public class ProcessMemoryScanner {

    /**
     * 根据进程名称获取进程ID
     * @param processName
     * @return int 进程ID
     */
    public static int getProcessIdByProcessName(String processName){
        int processId = 0;
        //获取 包括系统中快照中的所有进程 的快照
        WinNT.HANDLE processHandleSnapHost = Kernel32.INSTANCE.CreateToolhelp32Snapshot(
                Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0x00000000));
        //如果失败，会抛出异常。
        if (processHandleSnapHost == null || processHandleSnapHost.equals(WinBase.INVALID_HANDLE_VALUE)){
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
        //这是检索出来的进程条目信息
        Tlhelp32.PROCESSENTRY32 pe = new Tlhelp32.PROCESSENTRY32();
        //检索进程镜像
        // Process32First 从进程镜像信息中检索第一个进程的信息，结果存放在参数pe中，
        // Process32Next 检索系统内存快照中遇到的任何进程的下一个线程的相关信息。
        try {
            //System.out.println("进程名称");
            for(boolean success = Kernel32.INSTANCE.Process32First(processHandleSnapHost, pe);
                success;
                success = Kernel32.INSTANCE.Process32Next(processHandleSnapHost, pe)){
                //pe.szExeFile 的长度是260个字符，名称后面可能跟着一堆0，要截取掉。
                int index = pe.szExeFile.length - 1;
                for (int i = 0; i < index; i ++){
                    char item = pe.szExeFile[i];
                    if (item == 0){
                        index = i;
                        break;
                    }
                }
                //控制台打印进程名称
                //System.out.println(new String(pe.szExeFile, 0, index));
                //找到与参数指定的进程名称匹配的进程,并返回进程的ID
                if (processName.equals(new String(pe.szExeFile, 0, index))){
                    Kernel32.INSTANCE.CloseHandle(processHandleSnapHost);
                    //System.out.println(processName + "匹配到的进程ID: " +pe.th32ProcessID.intValue());
                    return pe.th32ProcessID.intValue();
                }
            }
        } catch (Throwable cause){
            throw  new Win32Exception(Kernel32.INSTANCE.GetLastError());
        } finally {
            Kernel32.INSTANCE.CloseHandle(processHandleSnapHost);
        }
        //走到这里就是检索失败，抛出一个异常，让调用方法知道失败了。
        throw  new RuntimeException("Process Not Exist");
    }

    /**
     * 根据进程ID获取进程信息
     * @param processId
     * @param beginAddr
     * @param endAddr
     */
    public static void EnumMemory(int processId, long beginAddr, long endAddr){
        //获取进程句柄    PROCESS_ALL_ACCESS
        WinNT.HANDLE processHandle ;
        //final WinDef.DWORD pageSize = new WinDef.DWORD(1024);
        final int pageSize = 1024;
        processHandle = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_ALL_ACCESS, false, processId);
        //内存页信息
        //WinDef.BYTE page[] = new WinDef.BYTE[pageSize.intValue()];
        Memory buffer = new Memory(pageSize);
        //临时的内存地址
        long tmpAddr = beginAddr;
        //已经读取了多少字节的内存
        IntByReference bytesReaded = new IntByReference();
        //保存最后一次的错误代码，保证只输出一次错误代码，避免错误提示输出过多，影响查看输出信息
        int lastErr = 0 ;
        //compareTo返回结果是：tmpAddr-endAddr
        while (tmpAddr <= endAddr){
            boolean success = Kernel32.INSTANCE.ReadProcessMemory(processHandle, new Pointer(tmpAddr),
                    buffer, pageSize,bytesReaded);
            // 处理结果
            if (success) {
                //System.out.printf("成功读取 %d 字节:\n", bytesReaded.getValue());
                //printHexDump(targetAddress, buffer, bytesReaded.getValue());
                //在控制台打印内存信息
                byte[] data = buffer.getByteArray(0, pageSize);
                for (int i = 0; i < pageSize; i++) {
                    // 每行开头打印地址
                    if (i % 16 == 0) {
                        System.out.printf("\n0x%08X | ", tmpAddr + i);
                    }
                    // 打印十六进制字节
                    System.out.printf("0x%02X ", data[i]);
                    // 在 8 字节处添加分隔符
                    //if (i % 16 == 7) System.out.print(" ");
                }
                System.out.println("\n");

            } else {
                if (lastErr != Kernel32.INSTANCE.GetLastError() ){
                    lastErr = Kernel32.INSTANCE.GetLastError();
                    System.err.printf("读取失败! 错误码: 0x%08X\n", lastErr);
                }
            }
            //指针向前移动
            tmpAddr += pageSize;
        }
    }

    /**
     * 枚举特定进程的内存块信息
     * @param processId
     */
    public static void ScanProcessMemory(int processId){
        //获取进程句柄
        WinNT.HANDLE processHandle ;
        processHandle = Kernel32.INSTANCE.OpenProcess(WinNT.PROCESS_ALL_ACCESS, false, processId);
        //内存基本信息结构
        WinNT.MEMORY_BASIC_INFORMATION  mbi = new WinNT.MEMORY_BASIC_INFORMATION();
        //操作系统相关信息结构体
        WinBase.SYSTEM_INFO systemInfo = new WinBase.SYSTEM_INFO();
        Kernel32.INSTANCE.GetNativeSystemInfo(systemInfo);
        //调试
        System.out.println("最低内存地址: " + systemInfo.lpMinimumApplicationAddress.toString());
        System.out.println("操作系统基本信息:");
        System.out.println(systemInfo.toString(true));
        System.out.println("操作系统基本信息获取结束.");
        System.out.println("------------------------------------------------------------------------ \n");
        System.out.println("开始地址 \t 结束地址 \t 大小 \t 状态 \t 内存类型 \t 页面属性 \n");
        System.out.println("------------------------------------------------------------------------ \n");
        // 判断只要当前地址小于最大地址就循环
        BaseTSD.SIZE_T size_t = new BaseTSD.SIZE_T();
        BaseTSD.SIZE_T size_t_mbi = new BaseTSD.SIZE_T(mbi.size());
        //判断一下，
        long lowAddr = Pointer.nativeValue(systemInfo.lpMinimumApplicationAddress);
        long highAddr = Pointer.nativeValue(systemInfo.lpMaximumApplicationAddress);
        while (lowAddr < highAddr){
            //初始化内存结构体
            mbi.clear();
            //查询内存属性 返回0：失败,size_t： 查询的内存字节长度
            //TODO: 1. processHandle没起作用？查询的实际进程比processHandle进程的大多了。
            size_t = Kernel32.INSTANCE.VirtualQueryEx(processHandle, new Pointer(lowAddr),mbi, size_t_mbi);
            //返回0，说明这页查询失败或者不能查询，跳到下一页查询
            if (size_t.longValue() == 0){
                lowAddr += systemInfo.dwPageSize.longValue();
                continue;
            }
            /**
             * 输出查询结果
             * mbi.baseAddress  ：   页面的基础地址
             * mbi.regionSize   :   从基础地址开始的区域大小（是整个页区域的大小吗？）
             */
            System.out.printf("0x%08X \t 0x%08X \t %8d K \t",
                    Pointer.nativeValue(mbi.baseAddress),
                    Pointer.nativeValue(mbi.baseAddress) + mbi.regionSize.longValue(),
                    mbi.regionSize.intValue() >> 10);
            //内存状态
            switch (mbi.state.intValue()){
                case WinNT.MEM_FREE :
                    System.out.print("空闲 \t");break;
                case WinNT.MEM_RESERVE :
                    System.out.print("保留 \t");break;
                case WinNT.MEM_COMMIT :
                    System.out.print("提交 \t");break;
                default:
                    System.out.print("未知 \t");break;
            }
            //内存类型
            switch (mbi.type.intValue()){
                case WinNT.MEM_PRIVATE :
                    System.out.print("私有 \t");break;
                case WinNT.MEM_MAPPED :
                    System.out.print("映射 \t");break;
                case WinNT.MEM_IMAGE :
                    System.out.print("镜像 \t");break;
                default:
                    System.out.print("未知 \t");break;
            }
            //页面属性（保护） 位运算符& 1&1 = 1， 0&*=0；
            if(mbi.protect.intValue() == 0){
                System.out.print("---");
            }else  if( (mbi.protect.intValue() & WinNT.PAGE_EXECUTE) != 0 ){
                System.out.print("E--");
            }else  if( (mbi.protect.intValue() & WinNT.PAGE_EXECUTE_READ) != 0 ){
                System.out.print("ER-");
            }else  if( (mbi.protect.intValue() & WinNT.PAGE_EXECUTE_READWRITE) != 0 ){
                System.out.print("ERW");
            }else  if( (mbi.protect.intValue() & WinNT.PAGE_READONLY) != 0 ){
                System.out.print("-R-");
            }else  if( (mbi.protect.intValue() & WinNT.PAGE_READWRITE) != 0 ){
                System.out.print("-RW");
            }else  if( (mbi.protect.intValue() & WinNT.PAGE_WRITECOPY) != 0 ){
                System.out.print("WCOPY");
            }
            //WinNT.PAGE_EXECUTE_WRITECOPY  永远走不到这里，因为与WinNT.PAGE_WRITECOPY值相同，都是0x80
            else  if( (mbi.protect.intValue() & 0x80) != 0 ){
                System.out.print("EWCOPY");
            }else{
                System.out.println("-" + mbi.protect.intValue() + "-");
            }
            System.out.print("\n");
            // 每次循环累加内存块的位置
            lowAddr += mbi.regionSize.longValue();
        }
        //关闭句柄
        Kernel32.INSTANCE.CloseHandle(processHandle);

    }

     //搜索内存中的整数示例
    //public static List<Long> searchIntInProcessMemory(int pid, int targetValue) throws Exception {
    //    final int PROCESS_QUERY_INFORMATION = 0x0400;
    //    final int PROCESS_VM_READ = 0x0010;
    //    Kernel32 kernel32 = Kernel32.INSTANCE;
    //    WinNT.HANDLE processHandle = kernel32.OpenProcess(PROCESS_QUERY_INFORMATION | PROCESS_VM_READ, false, pid);
    //    if (processHandle == null) {
    //        throw new Exception("无法打开进程，错误代码: " + Kernel32.INSTANCE.GetLastError());
    //    }
    //
    //    WinNT.MEMORY_BASIC_INFORMATION memoryInfo = new WinNT.MEMORY_BASIC_INFORMATION();
    //    Pointer currentAddress = Pointer.createConstant(0);
    //    List<Long> foundAddresses = new ArrayList<>();
    //    BaseTSD.SIZE_T size_t = new BaseTSD.SIZE_T(memoryInfo.size());
    //    /**
    //     * 这里，注意SIZE_T 和 int的不同。
    //     * VirtualQueryEx 的返回值是SIZE_T类型。
    //     */
    //    while (kernel32.VirtualQueryEx(processHandle, currentAddress, memoryInfo, size_t).longValue() != 0) {
    //        memoryInfo.state
    //        // 检查内存区域是否可读且已提交
    //        if (memoryInfo.state == WinNT.MEM_COMMIT && (memoryInfo.protect == WinNT.PAGE_READONLY
    //                || memoryInfo.protect == WinNT.PAGE_READWRITE)) {
    //            long regionSize = memoryInfo.regionSize.longValue();
    //            byte[] buffer = new byte[(int) regionSize];
    //            IntByReference bytesRead = new IntByReference();
    //
    //            if (kernel32.ReadProcessMemory(processHandle, memoryInfo.baseAddress, buffer, buffer.length, bytesRead)) {
    //                // 遍历缓冲区查找目标整数
    //                for (int i = 0; i <= buffer.length - 4; i++) {
    //                    int value = (buffer[i] & 0xFF) | ((buffer[i + 1] & 0xFF) << 8)
    //                            | ((buffer[i + 2] & 0xFF) << 16) | ((buffer[i + 3] & 0xFF) << 24);
    //                    if (value == targetValue) {
    //                        long foundAddr = memoryInfo.baseAddress.peer + i;
    //                        foundAddresses.add(foundAddr);
    //                    }
    //                }
    //            }
    //        }
    //        currentAddress = currentAddress.share(memoryInfo.regionSize.longValue());
    //    }
    //
    //    kernel32.CloseHandle(processHandle);
    //    return foundAddresses;
    //}

    public static void main(String[] args) {
        //注意：进程名称应该是可执行文件的名称，并不一定是在任务管理器中看到的名称，
        //例如IDEA的进程名称是：idea64.exe ， 而不是IntelliJ IDEA
        // win10 在任务管理器-》详细信息 里面查看进程的名称。
        //注意：一个程序打开了多个，那么只能查看到第一个进程的ID，
        String processName = "HelloWorld.exe";
        int processId = 0;
        try {
            processId = getProcessIdByProcessName(processName);
            System.out.println("进程名称: " + processName + ", 进程ID： " + processId);
        } catch (Exception e) {
            System.out.println("进程: " + processName + "不存在!");
            throw new RuntimeException(e);
        }
        //遍历指定进程ID的内存信息
        System.out.println("开始遍历进程:" + processName + "的内存信息");
        EnumMemory(processId,0x00401000, 0x7FFFFFFF);
        System.out.println("结束遍历进程:" + processName + "的内存信息");

        //枚举特定内存块的信息
        System.out.println("枚举进程: " + processName +" 的内存的详细信息:");
        ScanProcessMemory(processId);
        System.out.println("枚举进程内存详细信息技术.");


        //try {
        //    int pid = 1234; // 替换为目标进程的PID
        //    int targetValue = 42; // 替换为要查找的整数值
        //    List<Long> addresses = searchIntInProcessMemory(pid, targetValue);
        //    System.out.println("找到地址: " + addresses);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
    }
}
