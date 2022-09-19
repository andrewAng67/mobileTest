package com.example.iqueueapp

interface IQueuable {
    // Adds a new value to queue and returns new queue
    fun enqueue(value: String): Array<String>

    // Removes item from queue, and returns the item removed
    fun dequeue(): String

    // Returns a list of all the items in the queue
    fun getQueue(): Array<String>

    // Returns the number of items in the queue
    fun size():Number
}