package br.com.devtec.bingo.dominio.cartela.utils

enum class EnumCartela(val value:String) {

    ErroBanco("Não foi possível persistir os valores no banco de dados!!!"),
    InicieUmaCartela("Nenhuma cartela encontrada, por favor inicie uma Cartela!!!"),
    CartelaAtivaExite("Já existe uma cartela ativa, por favor verifique!!!")

}