# mobileTest

Repository for Quickdesk's Mobile App Developer Test
15th September 2022 -> 19th September 2022

# A. Building Queing Classes

## Part A

- Used arrays (arrayOf()) as main data structure to store elements into queue
- Difference between queue and stack `dequeue()`
  - **Queue** : Take element from array[0] and copy all elements from index 1 to index[last] (Copying over all elements except for the first element in the array which would be 'popped')
  - **Stack** : Take element from array[last] and copy all elements from index 0 to index[last-1] (Copying over all elements except for the last element in the array which would be 'popped')

## Part B

Instead of using array, we could also use linked lists to implement the queue. The linked list would consist of 2 pointers, one pointed at the first element of the list/head of the list, and the other at the last element of the list/tail of the list.

When we `enqueue()`, we simply add the element to where the tail of the list was, and we set that new element to be the new tail of the list.

This process works as well with the `dequeue()` function, where we simply add the element to where the head of the list was, and we set that new element to be the new head of the list.

## Part C

Due to the nature of the interface and the classes, I did not find anything that I could do to make the suggested implementation of both components to be any better.

---

# B. Build a simple carwash simulating mobile app

## Summary of app process (with slight tweaks from original spec)

* A customer can drop of their car by filling in their license plate number into the text field (Must be a valid Malaysian License plate number)
* The user will then get a unique ticket number in the form of `(licensePlate)-(random 3 digit number)`
* A customer doesn't collect their car by entering their ticket number, but rather the app shows which car is currently being washed, and where your car in the `QUEUE`
* A "WASH" task simulates one car being washed at a time. There are a total of 5 cars, including the user provided car for the demo

An apk has already been prepared for your testing convenience called IQueueDemo.apk (Requires Android 5.0 and above), due to some issues with building the project in Android Studio and testing it there via an emulator **IF** you are importing and opening the project from GitHub

## NOTE

- Requires openjdk-18
- I have issues importing this project from Github back onto my local device for some reason, so please let me know if this happens to your device as well
- However, there is an apk already prepared for your testing convenience called IQueueDemo.apk (Requires Android 5.0 and above)