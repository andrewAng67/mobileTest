package com.example.iqueueapp

class STACK : IQueuable{
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