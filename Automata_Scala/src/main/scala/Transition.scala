class Transition(initialState:Char, alphabet:Char, finalState:Char) {
  var firstState: Char = initialState
  var char: Char = alphabet
  var lastState : Char = finalState
  override def toString(): String = "(" + firstState + "," + char + "," + lastState+ ")"

}
