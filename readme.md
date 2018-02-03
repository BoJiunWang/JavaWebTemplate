## Introdution
* Due to lab is not allowed to using framework like Spring..., but we need a simple template to devlopement quickly.
* It's not a best template/design.
* Everyone can fully access this project, it's means you can commit/merge it when you find some bug or not fittable to be a template for lab.

## Known Issue
* 尚未處理CSRF(ESAPI有相關API可以使用，若之後有空會導入)

## 環境設定  

1. 開發階段  
將ESAPI.properties及validation.properties放入C:\Users\使用者名稱\esapi\資料夾底下
即可正常使用  

2. 佈署階段  

* 為順利使用OWASP ESAPI的XSS library，請在Tomcat的catalina.bat設定

```
set "JAVA_OPTS=%JAVA_OPTS% -Djava.protocol.handler.pkgs=org.apache.catalina.webresources -Dorg.owasp.esapi.resources="D:\Program Files\Tomcat\webapps\ROOT\WEB-INF\classes""
```
* -Dorg.owasp.esapi.resources路徑為網站部屬到Tomcat後ESAPI.properties和validation.properties兩個檔案的資料夾路徑

## Logger說明
* components.Looger提供logInfo、logWarn、logDebug三個Method，如有需要擴充可自行新增Metod。

* 使用logInfo、logWarn會被記錄到.log檔中，使用logDebug僅顯示於Console畫面

* Log檔以天為單位，當天之log會被命名為temp.log，其餘會依照日期命名，檔案存放位置預設為tomcat的logs資料夾中，  
  如需修改可在log4j2.xml的屬性進行修改

```
<Property name="log-path">${sys:catalina.home}/logs</Property>
```

## Coding Standard  
* 根據Google Java Style Guide進行編排，可下載intellij-java-google-style.xml，匯入Intellij IDEA的Code Style進行編排  
https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml  
* 可使用CheckStyle套件匯入Google Standard進行檢查
https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml

## FindBugs檢查
* 強烈建議專案通過FindBugs的檢查