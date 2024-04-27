class Automata(initialState:Char, finalStates:Array[Char],transitionsArg:Array[Transition]){
  //guardar argumentos
  var finalStes: Array[Char] = finalStates
  var transitions: Array[Transition] = transitionsArg

  /**
   *  Funcion que recibe un estado actual y un caracter de entrada y determina su estado destino.
   *  Si no hay estado final, regresa estado actual.
   * @param actualState estado actual
   * @param char caracter
   * @return estado actual
   */
  def pivote(actualState: Char, char: Char): Char = {
    //itera sobre las transiciones
    for (transition <- transitions) {
      //si una transicion coincide con el estado actual y el char
      if (actualState == transition.firstState && char == transition.char) {
        return transition.lastState; //devuelve estado final
      }
    }
    actualState  //de lo contrario regresa el estado actual
  }

  /**
   * Funcion que verifica si un estado es final. Si el estado es final, regresa true, de lo contrario regresa false
   * @param state estado
   * @return  el estado no es final
   */
  def isFinalState(state: Char): Boolean = {
    for (finalState <- finalStes) {
      if (state == finalState) {
        return true
      }
    }
    false
  }

}