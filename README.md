# Sauce Demo Project
1. Before/AfterMethod Before/AfterClass аннотации для pre post-conditions.
2. Data provider для тестов на айтемы и на логин.
3. Description для тестов.
4. RetryAnalizer и TestListener.
5. Для проекта SauceDemo добавлены suite.xml файлы:
*  В первом разделены тесты на два <test> блока, используя "groups", "classes"
*  Во втором продублированы два одинаковых "test" блока, но с разными "parameter name =“browser”" и органиизован запуск этих тестов на разных браузерах
*  В обоих сулучаях добавлен parallel и thread-count
6. Применен паттерн Page Object и Page Factory, а так же паттерн Сhainn of Invocations.
