1、在项目平级的目录，执行dos命令： 
xcopy project_dir project_dir_1 /s /i 

2、或者在项目根目录执行以下dos命令 
for /r . %%a in (.) do @if exist "%%a\.svn" rd /s /q "%%a\.svn" 