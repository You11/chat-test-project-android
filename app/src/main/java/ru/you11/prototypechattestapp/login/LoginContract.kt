package ru.you11.prototypechattestapp.login

import ru.you11.prototypechattestapp.BasePresenter
import ru.you11.prototypechattestapp.BaseView

interface LoginContract {

    interface View: BaseView<Presenter> {

    }

    interface Presenter: BasePresenter {

    }
}