// Andrew Ang
// QuickDesk: Mobile App Developer Test
// Part A - Build Queing Class
interface IQueuable {
    //adds value to queue and returns new queue
    fun enqueue(value: String): Array<String>
    //removes item from queue, and returns the item removed
    fun dequeue(): String
    //returns a list of all the items in the queue
    fun getQueue(): Array<String>
    //returns the number of items in the queue
    fun size():Number
}

class Queue : IQueuable{
    private var queue = arrayOf<String>()
    override fun enqueue(value: String): Array<String> {
        queue += value   // Add to the end
        return queue
    }

    override fun dequeue(): String {
        if (queue.isEmpty()){
            println("FIFO Queue is already empty")
            return ""
        }
        val dequed = queue.first()
        queue = queue.copyOfRange(1, queue.lastIndex + 1)  // Remove first element (skip first element)
        return dequed
    }

    override fun getQueue(): Array<String> {
        return queue.copyOfRange(0, queue.lastIndex + 1)
    }

    override fun size(): Int {
        return queue.size
    }

}

class Stack : IQueuable{
    private var stack = arrayOf<String>()
    override fun enqueue(value: String): Array<String> {
        stack += value
        return stack
    }

    override fun dequeue(): String {
        if (stack.isEmpty()) {
            println("LIFO Queue is already empty")
            return ""
        }
        val dequed = stack.last()
        stack = stack.copyOfRange(0, stack.lastIndex)
        return dequed
    }

    override fun getQueue(): Array<String> {
        return stack.copyOfRange(0, stack.lastIndex + 1)
    }

    override fun size(): Int {
        return stack.size
    }
}
fun main() {
    val queue = Queue()
    queue.enqueue("Apple")
    queue.enqueue("DOG")
    queue.enqueue("Chicken")

    println(queue.size())
    println(queue.dequeue())
    println(queue.dequeue())
    println(queue.dequeue())

    val stack = Stack()
    stack.enqueue("RATATATAT BAnana")
    stack.enqueue("Banana")
    stack.enqueue("Chicken")

    println(stack.size())
    println(stack.dequeue())
    println(stack.dequeue())
    println(stack.dequeue())

}