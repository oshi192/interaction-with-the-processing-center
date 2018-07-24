## Interaction-with-the-processing-center
This application implements interaction with the processing center LeoGaming through three operations: Verify, Payment and Status.
Each operation makes a POST request to the address [https://test.lgaming.net/external/extended](https://test.lgaming.net/external/extended). 

Request includes xml body and header "PayLogic-Signature" with digitally signed.
Each RESPONCE verify by digital signature.

#### XML tegs:

----------------------------------------------------------
 - **Request**
 
 Name | A type | Format | Required | Description
--- | --- | --- | --- | ---
point | attribute | ?? | true | Number of the payment acceptance point.
 
 **Request** teg contains one tag Verify or one Payment or many tags Status
 
 ----------------------------------------------------------
 - **Response**
 
 **Response** teg contains one or several tags Result
 
---------------------------------------------------------------------------- 
 - Verify (verification of the entered data by the subscriber):

Name | A type | Format | Required | Description
--- | --- | --- | --- | ---
service | attribute | Int4 | true |Service number
account |attribute |varchar100 |true  |Subscriber number in the system of the service provider
attribute  |element  | |false |Used to specify additional attributes. Maybe a few. The structure is the same as for Payment.

-----------------------------------------------
 - Attribute:
 
Name | A type | Format | Required | Description
--- | --- | --- | --- | ---
name | attribute | varchar50 | true | Attribute name
value | attribute | varchar100 | true | Attribute value

---------------------------------------
 - Payment
 
Name | A type | Format | Required | Description
--- | --- | --- | --- | ---
id | attribute | Int8 | true | The agent operation ID must be unique
sum | attribute | Int4 | true | Amount of replenishment of a personal account in kopecks
check | attribute | Int4 | true | Check number issued to the customer
service | attribute | Int4 | true | Service number
account | attribute | Varchar100 | true | Subscriber number in the system of the service provider
date | attribute | Time | true | Date of receipt of payment, used for reconciliation
attribute | element |  | false | Used to specify additional attributes. Maybe a few.

--------------------------------------
 - Status
 
 Name | A type | Format | Required | Description
--- | --- | --- | --- | ---
id | attribute | Int8 | true | Agent Operation ID

--------------------------------------
 - Result
 
 Name | A type | Format | Required | Description
--- | --- | --- | --- | ---
 id  | attribute  | int8  | false  | Идентификатор операции агента. Отсутствует в случае проверки номера абонента. 
state  | attribute |  int2  | false  | Статус платежа в системе, возвращается на запрос платежа и статуса 
substate  | attribute  | int2  | false  | Субстатус платежа в системе, возвращается на запрос платежа и статуса. 
code  | attribute  | int2  | true  | Код ошибки платежа или ошибка проверки реквизитов 
final  | attribute  | int2  | false  | Признак финальности статуса запроса (1 — фнальный) 
trans  | attribute  | int4  | false  | Номер транзакции ПЦ 
service  | attribute  | Int4  | false  | Результат проверки реквизитов у поставщика прй вызове advanced запросов. Актуален в случае возврата в атрибуте code значения 0. 
sum  | attribute  | Int4  | false  | Сумма платежа в копейках, возвращается при вызове метода сверки платежей 
commission  | attribute  | Int4  | false  | Сумма комиссии в копейках, возвращается при вызове метода расчета внешней комиссии 
sum_prov  | attribute  | Int4  | false  | Сумма транзакции в валюте поставщика 
server_time  | attribute  | Time  | false  | Время создания операции на сервере ПЦ, может использоваться для сверки с ПЦ 

--------------------------------------


### How to launch:
To start application, use one of the following steps:
#### in Linux
 - **use jar packajing:** <- preffered (need installed maven) 
    - download project or type in terminal: ```git clone https://github.com/oshi192/interaction-with-the-processing-center.git```
    - open terminal in sourse folder (where pom file is lying) and type the following:
    - ```mvn package```
    - ```java -jar target/TechTest-1.0-SNAPSHOT.jar ``` 
 - import to IntelliJ: 
    - download or ```git clone https://github.com/oshi192/interaction-with-the-processing-center.git```
    - needed instaled maven and lombok plugins 
    - fine->new->Project from existing source 

