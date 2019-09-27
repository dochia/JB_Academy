package board

import board.Direction.*


open class SquareBoardImpl (override val width: Int) : SquareBoard{
    private val cells: List<Cell>
    init {
        cells = mutableListOf<Cell>().apply{
        (0 until width * width).forEach{
           val row = it / width + 1
            val column = it % width + 1
            this.add(Cell(row, column))
        }}

    }
    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if (i <= 0 || j <= 0 || i > width || j > width)
            return null
        return cells[(i-1) * width + (j-1)]
    }

    override fun getCell(i: Int, j: Int): Cell {
        if (i <= 0 || j <= 0 || i > width || j > width)
            throw IllegalArgumentException()
        return cells[(i-1) * width + (j-1)]
    }

    override fun getAllCells(): Collection<Cell> {
        return cells.toList()
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val result = mutableListOf<Cell>()
        for (j in jRange) {
            val cell = getCellOrNull(i , j)
            println(cell)
            if (cell != null)
                result.add(cell)
        }
        return result
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val result = mutableListOf<Cell>()
        for (i in iRange){
            val cell = getCellOrNull(i, j)
            if (cell != null)
                result.add(cell)
        }
        return result
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        // the cell is at i-1, j-1
       return when (direction){
           UP -> getCellOrNull(this.i - 1,this.j )
           DOWN -> getCellOrNull(this.i + 1 , this.j )
           LEFT -> getCellOrNull(this.i , this.j - 1)
           RIGHT -> getCellOrNull(this.i ,this.j + 1)
        }
    }
}

class GameBoardImpl<T>(private val square: SquareBoard): GameBoard<T>, SquareBoard by square{
    override val width: Int
        get() = square.width

    private val values = mutableListOf<T?>().apply{
        (0 until width * width).forEach{ _ ->
            this.add(null)
        }
    }

    override fun get(cell: Cell): T? {
        return values[(cell.i - 1) * width + (cell.j - 1)]
    }

    override fun set(cell: Cell, value: T?) {
        values[(cell.i-1)  * width + (cell.j - 1)] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        val result = mutableListOf<Cell>()
        for ( (index, value) in values.withIndex())
            if (predicate(value)){
                val row = index / width + 1
                val col = index % width + 1
                result.add(square.getCell(row, col))
            }
        return result
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        for ((index, value) in values.withIndex())
            if (predicate(value))
                return square.getCell(index / width + 1 , index % width + 1)
        return null
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        for (value in values)
            if (predicate(value))
                return true
        return false
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        for (value in values)
            if (!predicate(value))
                return false
        return true
    }
}

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(createSquareBoard(width))

