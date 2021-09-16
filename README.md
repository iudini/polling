### Сборка приложения
- Для сборки приложения на вашем компьютере должны быть установлены:
    - JDK 8+
    - Maven
    - PostgreSQL
- Скачайте проект к себе на компьютер с помощью команды `git clone https://github.com/iudini/polling`
- В PostgreSQL создайте базу с именем "poll"
- В файле `src/main/resources/application.properties` укажите для параметра `poll.page-size` количество загружаемым опросов на страницу
- Выполните команду `mvn install`
- Далее `java -jar target/polling-0.1.jar`

Адрес сервера по умолчанию: http://localhost:8080/