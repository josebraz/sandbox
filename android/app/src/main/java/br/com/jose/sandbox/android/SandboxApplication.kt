package br.com.jose.sandbox.android

import android.app.Application
import br.com.jose.sandbox.android.data.source.RepositoryFactory
import br.com.jose.sandbox.android.data.source.UsersRepository

class SandboxApplication: Application() {

    val usersRepository: UsersRepository
        get() = RepositoryFactory.provideUsersRepository(this)

}