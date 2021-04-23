## About
My implementation of test task "Elevator in building".  

The building consists *n* number of floors, where *n* - is a random number generated at the start of the program in the range 5 <= *n* <= 20.
Each floor consists *K* number of people, where *k* - is a random number generated at the start of the program in the range 0 <= *k* <= 10.

Each passenger wants to arrive to a target floor which not equal to current.
Each floor has two buttons for calling the elevator "Up" and "Down", but not the lower and upper floors.

The lift has a capacity limit of 5 people max.
At the start, the elevator load people from the first floor and move from the current floor to the highest that passengers need.

On the way, the elevator stops at the floors needed by passengers, unload them and load new people who need to go in the same direction as the elevator moves.
Also, if the elevator has free place, it can stop on a floor where there are people who need to go in the same direction.

Моя реализация тестового завдания "Лифт в здании".  

Здание состоит из n-ого количества этажей, где n - случайное число генерируемое при старте программы в диапазоне 5 <= n <= 20.
На каждом этаже k-ое количество пассажиров, где k - случайное число генерируемое при старте программы в диапазоне 0 <= k <= 10.

Каждый пассажир хочет приехать на определённый этаж отличный от того на котором он находится.
На каждом этаже есть две кнопки для вызова лифта "Вверх" и "Вниз", исключение составляют нижний и верхний этажи.

Лифт имеет ограничение по вместимости: максимум 5 человек.
Первый раз лифт загружается людьми с первого этажа, и едет от первого (текущего) до наибольшего из тех, на которые нужно пассажирам.

По дороге лифт останавливается на тех этажах, на которых нужно пассажирам, высаживая их и подбирая людей, которым нужно в том же направлении, в котором движется лифт.
Также если лифт загружен не полностью, он может остановиться на этаже, на котором есть люди, которым необходимо в том же направлении.



## Build
To run the program you need:
* Java 8+
* Maven
* something to run bash scripts or IDE

Go to the project and run script:  
$bash start.sh  
or build and run it in your favorite IDE.

That's all.

## Authors
* Mykola Kostyshyn - [mikkiko](https://github.com/mikkiko) 