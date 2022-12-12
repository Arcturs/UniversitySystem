# Университетская система
## Краткое описание
Данный проект является лабораторной работой №10 по предмету "Проектирование и архитектура программных систем".
Разработан API для взаимодействия с университетской системой. Далее будут перечислены функциональные возможности для отдельных ролей:
- **Студент**: Просматривать информацию о факультетах, кафедрах, курсах и научных сотрудников кафедр;
- **Лектор**: Имеет возможность изменять информацию о курсе, который ведет;
- **Научный сотрудник, вовлеченный в проект**: Получать информацию о всех проектах, в которых принимает участие;
- **Декан**: Создавать новую кафедру факультета, а также изменять или удалять ее. Создавать и удалять проекты, а также назначать (или удалять) научного сотрудника в них. Создавать и удалять курсы, а также записывать (и удалять) в них лекторов;
- **Пользователь**: Входить и регистрироваться в системе;
- **Администратор**: Создавать, изменять и удалять факультет, а также назначать в нем декана. Вносить (и удалять) новых сотрудников.

## Использованные технологии:
- [Spring Boot](https://spring.io/projects/spring-boot);
- [Spring Security](https://spring.io/projects/spring-security);
- [Swagger documentation](https://swagger.io/);
- [PostgreSQL](https://www.postgresql.org/);
- [Liquibase](https://www.liquibase.org/);
- [JWT tokens](https://jwt.io/);
- [MapStruct](https://mapstruct.org/documentation/stable/reference/html/);
- [Docker](https://www.docker.com/).

## Запуск сервера
Для запуска сервера понадобится Docker. Для начала убедиться, что порты 5434 и 8080 не заняты. Далее в переменных окружениях (файл .env) указать ip адрес, на котором разворачивается докер. Если вы счастливый обладатель UNIX-системы, тогда измените следующую переменную окружения соответствующим образом:
```
DOCKER_IP=universitydb
```
Далее в папке проекта запустите следующую команду:
```
docker-compose up -d
```

## Эндпоинты
Список всех эндпоинтов можно увидеть в файле [openapi.yaml](/src/main/resources/static/openapi.yaml).

## Диаграммы
В данном разделе рассматриваются диаграммы, созданные на основе разработанного кода.
В ходе обратного инжиниринга была сгенерирована диаграмма классов: ![сlass_diagram.png](/diagrams/class_diagram.png)

Диаграмма прецедентов является иллюстрацией описания работы системы: ![use_case_diagram.png](/diagrams/use_case_diagram.png)

Диаграмма последовательностей поверхностно показывает работу приложения, а точнее как обрабатываются запросы пользователя: ![sequence_diagram.png](/diagrams/sequence_diagram.png)

Диаграмма состояний иллюстрирует как проходит обработка запроса пользователя. В качестве примера взят запрос на обновление курса: ![activity_diagram.png](/diagrams/activity_diagram.png)