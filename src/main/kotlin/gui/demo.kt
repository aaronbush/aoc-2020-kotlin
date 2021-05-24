package gui

import tornadofx.*

class MyApp : App(MyView::class) {

}

class MyView : View() {
    override val root = vbox {
        button("press me")
        label("Waiting")
    }

}

fun main() {
    launch<MyApp>()
}