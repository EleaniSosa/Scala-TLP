import Constants.FILE_PATH

import scala.io.Source
import scala.io.Source.fromFile
import scala.io.StdIn.readLine
object Operations {

  /**
   * Función que lee el contenido de un archivo y regresa su contenido
   * @param path dirección del archivo
   * @return contenido del archivo
   */
  private def readFile(path: String): String={
    fromFile(path).getLines().mkString
  }

  /**
   *  Función que recibe el contenido de un archivo y separa en estados iniciales, finales y transiciones
   * @param fileContent contenido del archivo
   * @return arrays que contienen la especificación formal de los automatas (transiciones, edoFinales, edoIniciales)
   */
  private def splitFileContent(fileContent: String): (Array[Transition], Char, Array[Char]) = {
  val parts = fileContent.split('|').map(_.replaceAll("'", ""))

  if (parts.length != 3) {
    throw new IllegalArgumentException("La cadena debe tener tres partes separadas por pipe (|)")
  }

  // Crear objetos Transition
  val transitions = parts(0).split(',').grouped(3)
    .map { case Array(initialState,char, finalState) =>
      new Transition(initialState.head, char.head, finalState.head)
    }.toArray

  val initialState  = parts(1).charAt(0)
  val finalStates = parts(2).split(',').flatMap(_.toCharArray) //.flatMap(state => state.toCharArray

  (transitions, initialState, finalStates)
 }

  /**
   * Funcion que imprime informacion de los automatas
   * @param transitions array de transiciones
   * @param initialState estado inicial
   * @param finalStates estado final
   */
  private def printAutomataInformation(transitions:Array[Transition],initialState:Char, finalStates:Array[Char]): Unit = {
    println("*** Especificación Formal del Autómata")
    println("Transiciones")
    transitions.foreach(println)

    println("Estado inicial")
    println(initialState)

    println("Estado(s) final(es)")
    finalStates.foreach(println)
  }

  /**
   *  Funcion que recibe la cadena y valida si pertenece o no al lenguaje
   * @param inputString cadena de entrada
   * @param automata automata
   * @param initialState estado inicial
   */
  private def processWord(inputString: String, automata: Automata, initialState: Char): Unit = {

    // Verificar si el usuario desea concluir
    if (inputString.toLowerCase() != "fin") {
      var actualState = initialState
      for (character <- inputString) {
        actualState = automata.pivote(actualState, character)
      }
      if (automata.isFinalState(actualState)) {
        println("\nPalabra aceptada")
      } else {
        println("\nPalabra rechazada")
      }
    } else {

    }
  }

  /**
   * Funcion que inicia el programa
   */
  def runProgram(): Unit = {
    println("******* Bienvenido al Programa ********")
    //Leer archivo
    val content = readFile(FILE_PATH)
    val (transitions, initialState, finalStates) = splitFileContent(content)

    //imprimir información en pantalla
    printAutomataInformation(transitions, initialState, finalStates)

    var inputString = ""

    while (inputString.toLowerCase() != "fin") {
      println("Ingrese una cadena (o escriba 'fin' para concluir): ")
      inputString = readLine()

      // Llamar a la función para validar y procesar la cadena de entrada
      if (inputString.toLowerCase() != "fin") {
        processWord(inputString, new Automata(initialState, finalStates, transitions), initialState)
      }
    }


  }


}
