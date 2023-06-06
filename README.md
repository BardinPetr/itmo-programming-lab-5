# ITMO Java Programming laboratory work #5 var 31192

*Файлы отчета:*

- `docs/report.pdf` - отчет по выполнению
- `docs/diagrams/img/*.svg` - UML диаграммы классов программного комплекса
- [Онлайн-документация](https://bardinpetr.github.io/itmo-programming-lab-5/)

# Задача:

> Реализовать консольное приложение, которое реализует управление коллекцией объектов в интерактивном режиме. В коллекции необходимо хранить объекты класса Worker, описание которого приведено ниже.

### Разработанная программа должна удовлетворять следующим требованиям:

* Класс, коллекцией экземпляров которого управляет программа, должен реализовывать сортировку по умолчанию.
* Все требования к полям класса (указанные в виде комментариев) должны быть выполнены.
* Для хранения необходимо использовать коллекцию типа java.util.TreeSet
* При запуске приложения коллекция должна автоматически заполняться значениями из файла.
* Имя файла должно передаваться программе с помощью: аргумент командной строки.
* Данные должны храниться в файле в формате xml
* Чтение данных из файла необходимо реализовать с помощью класса java.io.InputStreamReader
* Запись данных в файл необходимо реализовать с помощью класса java.io.OutputStreamWriter
* Все классы в программе должны быть задокументированы в формате javadoc.
* Программа должна корректно работать с неправильными данными (ошибки пользовательского ввода, отсутсвие прав доступа к файлу и т.п.).
* В интерактивном режиме программа должна поддерживать выполнение следующих команд:

```
help : вывести справку по доступным командам
info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
add {element} : добавить новый элемент в коллекцию
update id {element} : обновить значение элемента коллекции, id которого равен заданному
remove_by_id id : удалить элемент из коллекции по его id
clear : очистить коллекцию
save : сохранить коллекцию в файл
execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
exit : завершить программу (без сохранения в файл)
add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
filter_less_than_position position : вывести элементы, значение поля position которых меньше заданного
print_descending : вывести элементы коллекции в порядке убывания
print_unique_organization : вывести уникальные значения поля organization всех элементов в коллекции
```

#### Формат ввода команд:

* Все аргументы команды, являющиеся стандартными типами данных (примитивные типы, классы-оболочки, String, классы для хранения дат), должны вводиться в той же строке, что и имя команды.
* Все составные типы данных (объекты классов, хранящиеся в коллекции) должны вводиться по одному полю в строку.
* При вводе составных типов данных пользователю должно показываться приглашение к вводу, содержащее имя поля (например, "Введите дату рождения:")
* Если поле является enum'ом, то вводится имя одной из его констант (при этом список констант должен быть предварительно выведен).
* При некорректном пользовательском вводе (введена строка, не являющаяся именем константы в enum'е; введена строка вместо числа; введённое число не входит в указанные границы и т.п.) должно быть показано сообщение об ошибке и предложено повторить ввод поля.
* Для ввода значений null использовать пустую строку.
* Поля с комментарием "Значение этого поля должно генерироваться автоматически" не должны вводиться пользователем вручную при добавлении.

## Описание хранимых в коллекции классов:

```java
public class Worker {
  private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
  private String name; //Поле не может быть null, Строка не может быть пустой
  private Coordinates coordinates; //Поле не может быть null
  private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
  private float salary; //Значение поля должно быть больше 0
  private java.util.Date startDate; //Поле не может быть null
  private java.time.LocalDate endDate; //Поле может быть null
  private Position position; //Поле может быть null
  private Organization organization; //Поле может быть null
}

public class Coordinates {
  private Integer x; //Максимальное значение поля: 773, Поле не может быть null
  private float y; //Значение поля должно быть больше -413
}

public class Organization {
  private String fullName; //Поле не может быть null
  private OrganizationType type; //Поле не может быть null
}

public enum Position {
  ENGINEER,
  HEAD_OF_DEPARTMENT,
  LEAD_DEVELOPER,
  CLEANER,
  MANAGER_OF_CLEANING
}

public enum OrganizationType {
  COMMERCIAL,
  PUBLIC,
  PRIVATE_LIMITED_COMPANY,
  OPEN_JOINT_STOCK_COMPANY
}
```

## Отчёт по работе должен содержать:

* Текст задания.
* Диаграмма классов разработанной программы.
* Исходный код программы.
* Выводы по работе.
* Вопросы к защите лабораторной работы:
    * Коллекции. Сортировка элементов коллекции. Интерфейсы java.util.Comparable и java.util.Comparator.
    * Категории коллекций - списки, множества. Интерфейс java.util.Map и его реализации.
    * Параметризованные типы. Создание параметризуемых классов. Wildcard-параметры.
    * Классы-оболочки. Назначение, область применения, преимущества и недостатки. Автоупаковка и автораспаковка.
    * Потоки ввода-вывода в Java. Байтовые и символьные потоки. "Цепочки" потоков (Stream Chains).
    * Работа с файлами в Java. Класс java.io.File.
    * Пакет java.nio - назначение, основные классы и интерфейсы.
    * Утилита javadoc. Особенности автоматического документирования кода в Java.

# Подзадание №2 вариант №31244

Разделить программу из лабораторной работы №5 на клиентский и серверный модули. Серверный модуль должен осуществлять выполнение команд по управлению коллекцией. Клиентский модуль должен в интерактивном режиме считывать команды, передавать их для выполнения на сервер и выводить результаты выполнения.

## Необходимо выполнить следующие требования:

* Операции обработки объектов коллекции должны быть реализованы с помощью Stream API с использованием лямбда-выражений.
* Объекты между клиентом и сервером должны передаваться в сериализованном виде.
* Объекты в коллекции, передаваемой клиенту, должны быть отсортированы по умолчанию
* Клиент должен корректно обрабатывать временную недоступность сервера.
* Обмен данными между клиентом и сервером должен осуществляться по протоколу UDP
* Для обмена данными на сервере необходимо использовать датаграммы
* Для обмена данными на клиенте необходимо использовать сетевой канал
* Сетевые каналы должны использоваться в неблокирующем режиме.

## Обязанности серверного приложения:

* Работа с файлом, хранящим коллекцию.
* Управление коллекцией объектов.
* Назначение автоматически генерируемых полей объектов в коллекции.
* Ожидание подключений и запросов от клиента.
* Обработка полученных запросов (команд).
* Сохранение коллекции в файл при завершении работы приложения.
* Сохранение коллекции в файл при исполнении специальной команды, доступной только серверу (клиент такую команду отправить не может).
* Серверное приложение должно состоять из следующих модулей (реализованных в виде одного или нескольких классов):
* Модуль приёма подключений.
* Модуль чтения запроса.
* Модуль обработки полученных команд.
* Модуль отправки ответов клиенту.
* Сервер должен работать в однопоточном режиме.

## Обязанности клиентского приложения:

* Чтение команд из консоли.
* Валидация вводимых данных.
* Сериализация введённой команды и её аргументов.
* Отправка полученной команды и её аргументов на сервер.
* Обработка ответа от сервера (вывод результата исполнения команды в консоль).
* Команду save из клиентского приложения необходимо убрать.
* Команда exit завершает работу клиентского приложения.

_Важно! Команды и их аргументы должны представлять из себя объекты классов. Недопустим обмен "простыми" строками. Так,
для команды add или её аналога необходимо сформировать объект, содержащий тип команды и объект, который должен храниться
в вашей коллекции._

## Дополнительное задание:

Реализовать логирование различных этапов работы сервера (начало работы, получение нового подключения, получение нового запроса, отправка ответа и т.п.) с помощью Java Util Logging

## Отчёт по работе должен содержать:

Текст задания.

* Диаграмма классов разработанной программы (как клиентского, так и серверного приложения).
* Исходный код программы.
* Выводы по работе.
* Вопросы к защите лабораторной работы:
    * Сетевое взаимодействие - клиент-серверная архитектура, основные протоколы, их сходства и отличия.
    * Протокол TCP. Классы Socket и ServerSocket.
    * Протокол UDP. Классы DatagramSocket и DatagramPacket.
    * Отличия блокирующего и неблокирующего ввода-вывода, их преимущества и недостатки. Работа с сетевыми каналами.
    * Классы SocketChannel и DatagramChannel.
    * Передача данных по сети. Сериализация объектов.
    * Интерфейс Serializable. Объектный граф, сериализация и десериализация полей и методов.
    * Java Stream API. Создание конвейеров. Промежуточные и терминальные операции.
    * Шаблоны проектирования: Decorator, Iterator, Factory method, Command, Flyweight, Interpreter, Singleton, Strategy, Adapter, Facade, Proxy.

# Подзадание №2 вариант №432344

Доработать программу из лабораторной работы №6 следующим образом:

Организовать хранение коллекции в реляционной СУБД (PostgresQL). Убрать хранение коллекции в файле.
Для генерации поля id использовать средства базы данных (sequence).
Обновлять состояние коллекции в памяти только при успешном добавлении объекта в БД
Все команды получения данных должны работать с коллекцией в памяти, а не в БД
Организовать возможность регистрации и авторизации пользователей. У пользователя есть возможность указать пароль.
Пароли при хранении хэшировать алгоритмом SHA-256
Запретить выполнение команд не авторизованным пользователям.
При хранении объектов сохранять информацию о пользователе, который создал этот объект.
Пользователи должны иметь возможность просмотра всех объектов коллекции, но модифицировать могут только принадлежащие им.
Для идентификации пользователя отправлять логин и пароль с каждым запросом.
Необходимо реализовать многопоточную обработку запросов.

Для многопоточного чтения запросов использовать ForkJoinPool
Для многопотчной обработки полученного запроса использовать создание нового потока (java.lang.Thread)
Для многопоточной отправки ответа использовать создание нового потока (java.lang.Thread)
Для синхронизации доступа к коллекции использовать синхронизацию чтения и записи с помощью java.util.concurrent.locks.ReadWriteLock
Порядок выполнения работы:

В качестве базы данных использовать PostgreSQL.
Для подключения к БД на кафедральном сервере использовать хост pg, имя базы данных - studs, имя пользователя/пароль совпадают с таковыми для подключения к серверу.
Отчёт по работе должен содержать:

Текст задания.
Диаграмма классов разработанной программы.
Исходный код программы.
Выводы по работе.
Вопросы к защите лабораторной работы:

Многопоточность. Класс Thread, интерфейс Runnable. Модификатор synchronized.
Методы wait(), notify() класса Object, интерфейсы Lock и Condition.
Классы-сихронизаторы из пакета java.util.concurrent.
Модификатор volatile. Атомарные типы данных и операции.
Коллекции из пакета java.util.concurrent.
Интерфейсы Executor, ExecutorService, Callable, Future
Пулы потоков
JDBC. Порядок взаимодействия с базой данных. Класс DriverManager. Интерфейс Connection
Интерфейсы Statement, PreparedStatement, ResultSet, RowSet
Шаблоны проектирования.


# Подзадание №4 вариант №432344

Интерфейс должен быть реализован с помощью библиотеки Swing
Графический интерфейс клиентской части должен поддерживать русский, финский, венгерский и испанский (Колумбия) языки / локали. Должно обеспечиваться корректное отображение чисел, даты и времени в соответстии с локалью. Переключение языков должно происходить без перезапуска приложения. Локализованные ресурсы должны храниться в классе.
Доработать программу из лабораторной работы №7 следующим образом:

Заменить консольный клиент на клиент с графическим интерфейсом пользователя(GUI).
В функционал клиента должно входить:

Окно с авторизацией/регистрацией.
Отображение текущего пользователя.
Таблица, отображающая все объекты из коллекции
Каждое поле объекта - отдельная колонка таблицы.
Строки таблицы можно фильтровать/сортировать по значениям любой из колонок. Сортировку и фильтрацию значений столбцов реализовать с помощью Streams API.
Поддержка всех команд из предыдущих лабораторных работ.
Область, визуализирующую объекты коллекции
Объекты должны быть нарисованы с помощью графических примитивов с использованием Graphics, Canvas или аналогичных средств графической библиотеки.
При визуализации использовать данные о координатах и размерах объекта.
Объекты от разных пользователей должны быть нарисованы разными цветами.
При нажатии на объект должна выводиться информация об этом объекте.
При добавлении/удалении/изменении объекта, он должен автоматически появиться/исчезнуть/измениться на области как владельца, так и всех других клиентов.
При отрисовке объекта должна воспроизводиться согласованная с преподавателем анимация.
Возможность редактирования отдельных полей любого из объектов (принадлежащего пользователю). Переход к редактированию объекта возможен из таблицы с общим списком объектов и из области с визуализацией объекта.
Возможность удаления выбранного объекта (даже если команды remove ранее не было).
Перед непосредственной разработкой приложения необходимо согласовать прототип интерфейса с преподавателем. Прототип интерфейса должен быть создан с помощью средства для построения прототипов интерфейсов(mockplus, draw.io, etc.)

Вопросы к защите лабораторной работы:

Компоненты пользовательского интерфейса. Иерархия компонентов.
Базовые классы Component, Container, JComponent.
Менеджеры компоновки.
Модель обработки событий. Класс-слушатель и класс-событие.
Технология JavaFX. Особенности архитектуры, отличия от AWT / Swing.
Интернационализация. Локализация. Хранение локализованных ресурсов.
Форматирование локализованных числовых данных, текста, даты и времени. Классы NumberFormat, DateFormat, MessageFormat, ChoiceFormat.
