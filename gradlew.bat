@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the &quot;License&quot;);
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if &quot;%DEBUG%&quot; == &quot;true&quot; @echo on
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if &quot;%OS%&quot;==&quot;Windows_NT&quot; setlocal

set DIRNAME=%~dp0
if &quot;%DIRNAME%&quot; == &quot;&quot; set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any &quot;\&quot; in paths for Win32 interop
if exist &quot;%APP_HOME%\jre&quot; set APP_HOME=%APP_HOME%\jre

@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version &gt;NUL 2&gt;&amp;1
if &quot;%ERRORLEVEL%&quot; == &quot;0&quot; goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:&quot;=&quot;%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist &quot;%JAVA_EXE%&quot; goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar


@rem Execute Gradle
&quot;%JAVA_EXE%&quot; %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% &quot;-Dorg.gradle.appname=%APP_BASE_NAME%&quot; -classpath &quot;%CLASSPATH%&quot; org.gradle.wrapper.GradleWrapperMain %*

:end
@rem End local scope for the variables.
if &quot;%ERRORLEVEL%&quot;==0 goto mainEnd

:fail
rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd_ return code!
if not &quot;&quot; == &quot;%GRADLE_EXIT_CONSOLE%&quot; exit 1
exit /b 1

:mainEnd
if &quot;%OS%&quot;==&quot;Windows_NT&quot; endlocal

:omega