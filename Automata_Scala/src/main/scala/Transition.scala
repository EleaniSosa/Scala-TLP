class Transition(initialState:Char, letter:Char, finalState:Char) {
  var firstState: Char = initialState
  var char: Char = letter
  var lastState : Char = finalState
  override def toString(): String = "(" + firstState + "," + char + "," + lastState+ ")"

}
