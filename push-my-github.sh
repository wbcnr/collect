#!/bin/bash
# 注意，请先克隆github仓库，并在仓库文件内执行此文件。
# 更新本地仓库 ，并 推送到远程github仓库。
# 查看历史commit
# git log --pretty=oneline --abbrev-commit

git add * 
git commit -m "增加一个项目xx-slf4j-demo"
# 给commit打标签
git tag Ver20250508-2

# 设置全局用户名与邮件地址
#git config --global user.name "wbcnr"
#git config --global user.email "chenojing@qq.com"

# 推送到远程github仓库。
git push -u origin main
