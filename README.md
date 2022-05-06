# Introduction 

**Top Gun**  - is JAVA test automation framework in Sartorius company. 

Contains GUI test cases based on following tools:
1. Playwright + Java 
   - Source: https://playwright.dev/java/docs/intro
2. Cucumber
    - Source: https://cucumber.io/docs/installation/java/



Tips:
1. Open PowerShell
`mvn exec:java -e "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=codegen https://staging.umetrics.studio/"` - autogenerate code
`mvn exec:java -e "-Dexec.mainClass=com.microsoft.playwright.CLI" "-Dexec.args=show-trace trace.zip"` - open trace or https://trace.playwright.dev/