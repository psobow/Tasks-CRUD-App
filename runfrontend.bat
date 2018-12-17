::przed wykonaniem tego skryptu uruchom ręcznie serwer MAMP
:: front end na mampie wysyła żądania na http://localhost:8080/crud/v1...

call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo runcrud.bat has errors - breaking work
goto fail

:openbrowser
start "" "http://localhost:8888/tasks_frontend/"
if "%ERRORLEVEL%" == "0" goto end

:fail
echo.
echo There were errors

:end
echo.
echo runfrontend is finished.