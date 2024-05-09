package output

import entity.UserEntity

class Consola :IOutputiinfo{
    override fun showMenssage(mensaje: String, lineBreak: Boolean) {
        if (lineBreak){
            println(mensaje)
        }else{
            print(mensaje)
        }
    }

    override fun show(userList: List<UserEntity>, mensaje: String) {
        if (userList.isNotEmpty()){
            showMenssage(mensaje,true)
            var cont = 0
            userList.forEach {
                showMenssage("       ${++cont} Id : ${it.id} , nombre : ${it.name} y email : ${it.email}",true)
            }
        }
    }

}