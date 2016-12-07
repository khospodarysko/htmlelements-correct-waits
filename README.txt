Page.initElements - ініціює всі @FindBy and @Block елементи з тим декораторм, який переданий в метод

    HtmlElementDecorator(SearchContext) - має метод decorate який декорує поле класу в залежності чи це yandex чи webelement чи список

        HtmlElementLocatorFactory(SearchContext) - створює локатор обєкт

            AjaxElementLocator
                має findElement метод, що шукає елемент - і тут чекається тайм аут 5 секунд і прокидається NoSuchElementException
                зроблено через SlowLoadableComponent всередині

                SlowLoadableComponent
                    на протязі 5 секунд кожні 250 мілісекунд шукає елемент
                    if implicitWait is set then waiting time is doubled because of SlowLoadableComponent.get() implementation
                    first time it wait for element and if it is present returns (isLoaded())
                    secode it wait in a while loop for that element and calls isLoaded durint timeout that was set somewhere else in test code

Мені якраз підходить перехоплювати ексепшини у врапер-класах, бо якщо елемент не знайдено через findElement - то це не добре, і після цього
ніякий екшн не може бути виконаний, а якщо елемент знайдено, то зазвичай клік чи інший екшн не спрацьовує - ось там то і можна переловлювати
(як і зроблено в тайдмарка через прості врапери типу Input)

    page class that has @FindBy and @Block elements



Yandex
- по замовчуванню таймаут = 5 секунд, можна через @Timeout поставити кастомно, але що - ставити на кожен елемент/блок ?
драйвер.імплісіт вейт ще додає затримку до 5 секунд, але це не просто додавання, а по якісь формулі воно росте
