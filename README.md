# HomeLibrarian
##Проект интернет-библиотеки

</br>
Обзор: Реализовать библиотеку с web-интерфейсом
Технологии 
--------------------------------
* Java
* PostgreSQL
* Hibernate
* Vaadin
* Maven
* Работа с почтой javax.mail.
* Система контроля версий Github
* Тестирование Junit
* Сервер Tomcat или Jetty
* Таблицы стилей CSS


Настройка среды разработки 
--------------------------------------
1. Установить intellij idea 14++
2. Устаность PGAdmin 1.20.0 [ССЫЛКА](http://www.pgadmin.org/)
    * Создать там пользователя логин: postgres пароль: Iround!1 
    * Создать таблицу с именем homelibbd и владельцем postgres
    * Порт должен быть 5432
3. Зарегистрироваться на github
4. Установить Git [ССЫЛКА](https://git-for-windows.github.io/)
5. Подключиться из intellij idea к нужной репозитории и загрузить проект
    * Ставим Java 7, Maven, Git;
    * VCS > Checkout from Version Control > GitHub > Ввести пароль;
    * Выбираете репозиторий https://github.com/Likemilk99/HomeLibrarian.git > выбираете Parent Directory и название проекта > Clone.
    * После клонирования вам предложат открыть проект, тыцаете Yes
    * Откройте проект > Enable Auto Import
    * VCS > Git > Branches > origin/test > Checkout as new local branch > OK. 
6. JDK 1.8
7. PostgreSQL 9.4.5
8. Сборка проекта:
   * vaadin clean-compile
   * livecircle: clean-compile
   * jetty: run
   * Проверить пути к файлам в тестах

Дополнительное ПО 
--------------------------------------
1. Отрисовка схем - [ТЫКК] (https://www.draw.io/)
2. 
