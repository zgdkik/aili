1������Ŀƽ����Ŀ¼��ִ��dos��� 
xcopy project_dir project_dir_1 /s /i 

2����������Ŀ��Ŀ¼ִ������dos���� 
for /r . %%a in (.) do @if exist "%%a\.svn" rd /s /q "%%a\.svn" 