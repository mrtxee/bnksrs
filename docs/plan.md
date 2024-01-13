# План разработки

1. Делаю ядро системы, которая умеет хранить данные и отвечать на rest-запросы
2. Добавляю тесты согласно ТЗ
3. Разбиваю ядро на микросервисы `AccountInfoService`, `BankingService`
4. Настраиваю взаимодействие между сервисами при помощи `Kafka`
5. При помощи Сереги делаем оценку, похож ли код на то что будет круто смотреться на собесе
6. ? Деплой при помощи Docker контейнеров
    1. В том случае, если это демонстрабельно на собесе

## AccountInfoService

### База данных

- таблица `clients`
    - ID autoincremental
    - name
    - familyname
    - fathername
    - iddoc_type ENUM(PASSPORT_RU, PASSPORT_KZ, PASSPORT_INERNATIONAL)
    - iddoc_number varchar
    - iddoc_payload varchar
- таблица `account`
    - ID
    - clientID :: clients.ID
    - client_fullname :: CONCAT(client.name,” ”,client.name2,” ”,client.name3)
    - accountID
    - currency ENUM(RUR, USD, EUR)
    - amount long
    - decimal_factor byte
    - status ENUM(active, locked)

### Rest API

1. зарегистрировать клиента `HTTP POST /client/`
2. зарегистрировать счет для клиента `HTTP POST /account/`
    1. номер счета accountID генерируется автоматически: “4003”+<8 рандомных цифр>
3. узнать остаток на счете `HTTP GET /account/<accountID:int>/`
4. получить данные о клиенте `HTTP GET /client/<ID:int>/`
    1. вернуть пользователя и список его счетов

## BankingService

### Rest API

1. перевод суммы `HTTP POST /transaction/`
    1. проверки:
        1. не заблокированы счета
        2. совпадают валюты счетов
        3. достаточный свободный остаток

### База данных

- таблица `transations`
    - ID autoincremental
    - timestamp — autogenereted by DB
    - sender
    - recipient
    - amount long
    - decimal_factor byte
    - payload