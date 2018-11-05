call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo runcrud.bat has errors - breaking work
goto fail

:openbrowser
start "" "http://localhost:8080/crud/v1/tasks"
if "%ERRORLEVEL%" == "0" goto end

:fail
echo.
echo There were errors

:end
echo.
echo showtasks is finished.