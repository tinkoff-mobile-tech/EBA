# Eba Architecture Sample

Приложение, демонстрирующее основную концепцию архитектуры EBA.
В нем представлена простая реализация поиска по заранее сгенерированным элементам, 
а также простой сценарий использования Eba на примере экрана <a href="https://github.com/Jacks0N23/EBA/blob/master/sample-eba/src/main/java/ru/tinkoff/sample_eba/about/AboutFragment.kt">About</a>.

![](preview/EBA-sample.gif)

# Концепция

<strong>EBA &mdash; это аббревиатура трех основных элементов архитектуры: </strong>

<li><strong>Event</strong> &mdash; любое событие, которое приходит в результате взаимодействия пользователя с приложением</li>

<li><strong>Action</strong> &mdash; любое действие, которое выполняет приложение в ответ на события</li>

<li><strong>Binder</strong> &mdash; то, что связывает события и действия (преобразует Event в Action)</li>

# Action Creator

<li>  Любое действие, которое выполняется в ответ на какое-либо событие может быть представлено цепочкой или композицией других действий (например, в ответ на событие pull-to-refresh, мы хотим показать какой-нибудь Toast, другой индикатор загрузки, а в конце отобразить обновленные данные) &mdash; этим как раз занимается <a href="https://github.com/Jacks0N23/EBA/blob/master/sample-eba/src/main/java/ru/tinkoff/sample_eba/search/LoadSomeDataActionCreator.kt">ActionCreator</a></li>

# Подключение
Для подключения добавьте в [_build.gradle_] вашего проекта следующую зависимость:
```groovy
implementation 'ru.tinkoff.eba:EBA:1.0.0'
```

# Используемые библиотеки

<li> <a href="https://github.com/ReactiveX/RxJava">RxJava</a></li>
<li> <a href="https://github.com/JakeWharton/RxBinding">RxBinding</a></li>

# Ссылка на статью
<a href="https://habr.com/ru/company/tinkoff/blog/474612/">Архитектура EBA aka реактивность на всю катушку</a>
