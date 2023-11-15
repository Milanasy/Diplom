# Процедура запуска автотестов: 

1. С помощью команды `git clone` необходимо склонировать проект с GitHub по ссылке:
 [git@github.com:Milanasy/Diplom.git]();
 
2. Открыть проект с помощью программы: "**IntelliJ IDEA**";

3. Необходимо проверить две СУБД: **MySQL** и **PostgreSQL**.

### MySQL:
1. Запустить контейнер с помощью команды: `docker-compose up`
2. Запустить приложение с помощью команды: 
`java '-D spring.datasource.url=jdbc:mysql://localhost:3300/app' -jar ./artifacts/aqa-shop.jar` 
3. Запустить тесты с помощью команды 
`./gradlew clean test '-Ddb.url=jdbc:mysql://localhost:3300/app'`
4. Сформировать отчет с помощью команды: `./gradlew allureReport`
5. Открыть отчёт в браузере с помощью команды: `./gradlew allureServe`
6. Остановить контейнер с помощью команды: `docker compose down`

### PostgreSQL:
1. Запустить контейнер с помощью команды: `docker-compose up`
2. Запустить приложение с помощью команды: 
`Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar .\artifacts\aqa-shop.jar`
3. Запустить тесты с помощью команды:
`./gradlew clean test '-Ddb.url=jdbc:postgresql://localhost:5432/app'`
4. Сформировать отчет с помощью команды: `./gradlew allureReport`
5. Открыть отчёт в браузере с помощью команды: `./gradlew allureServe`
6. Остановить контейнер с помощью команды: `docker compose down`