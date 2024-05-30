# Банковское приложение
## Структура проекта
### controller

`UserBalanceController` - REST Controller

### exception
`GlobalExceptionHandling` - класс, предоставляющий функциональность Global Exception Handler-a<br>
`IncorrectData` - тип объекта, который добавляется в HTTP Response Body<br>
`NoSuchElementException` - exception, на который должен реагировать метод<br>
`NotEnoughMiney` - exception, на который должен реагировать метод<br>
`NegativeAmount` - exception, на который должен реагировать метод

### model
`UserBalance` - pojo-класс UserBalance<br>
`Operation` - pojo-класс Operation<br>
`OperationType` - enum для Operation<br>

### repository
`UserBalanceRepository` - слой DAO, для взаимодействия с БД<br>
`OperationRepository` - слой DAO, для взаимодействия с БД

### service
`OperationService`<br>
`UserBalanceService`

### mapper
`OperationMapper`<br>
`UserBalanceMapper`

### dto
`ErrorDTO`<br>
`FieldErrorDTO`<br>
`OperationDTO`<br>
`UserBalanceDTO`

### test
`CommonTest`<br>
`UserBalanceControllerTest` - класс для тестирования RestController

#### application.property
Файл для хранения параметров конфигурации приложения<br>
#### Структура БД

![Screenshot](https://github.com/AlinaMam/RESTapi_skillfactory/blob/master/src/main/resources/images/Структура%20БД.png)



