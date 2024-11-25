package org.example.br.com.estudoalura.alugames

import br.com.estudoalura.alugames.models.InfoJogo
import br.com.estudoalura.alugames.models.Jogo
import br.com.estudoalura.alugames.services.ConsumoApi
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar: ")
    val busca = leitura.nextLine()

    val buscaApi = ConsumoApi()
    val informacaoJogo = buscaApi.buscarJogo(busca)

    var meuJogo: Jogo? = null

    val resultado = runCatching{
        if (informacaoJogo != null) {
            meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb)
        }

    }

    resultado.onFailure {
        println("Jogo inexistente, tente outro ID")
    }

    resultado.onSuccess {
        println("Deseja inserir uma descrição personalizada? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", true)) {
            println("Insira a descrição personalizada para o jogo:")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        } else {
            meuJogo?.descricao = meuJogo?.titulo
        }

        println(meuJogo)
    }
    resultado.onSuccess {
        println("\n Busca finalizada com sucesso.")
    }
}