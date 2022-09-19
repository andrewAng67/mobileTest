package com.example.iqueueapp

class QUEUE : IQueuable{
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

fun queueSetup(newNumber : String): QUEUE{
    var queue = QUEUE()
    queue.enqueue("AMZ2590-123")
    queue.enqueue("DBA90-988")
    queue.enqueue("WMG671-222")
    queue.enqueue("VHJ0001-121")
    queue.enqueue(newNumber)
    return queue
}