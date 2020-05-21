# kotlinx-coroutine-rx1

Kotlinx-coroutine-rx1 is a simple utility library which provides APIs to convert RxJava 1 reactive types to Kotlin coroutines or Flow and vice versa.

The original credit goes to [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) repository.

## Why
This library is for people who are still stuck with RxJava 1 (believe me there are some people) and makes it easy to gradually migrate from 
RxJava 1 to coroutines and Flow.

Originally some APIs were added in [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) repository to provide support
for people with RxJava 1 and later were removed.
This repository is just continuation of the same work.

## Installation

The dependency can added directly from Sonatype. Currently, it's only available on Sonatype's snapshot repo

1. Add the following in your root/build.gradle file to fetch from snapshot repository:

```groovy
repositories {
  maven { url 'http://oss.sonatype.org/content/repositories/snapshots' }
}
```

2. Then add the following dependency in your module/build.gradle file

```groovy
dependencies {
  implementation "com.github.akshaychordiya.kotlinx.coroutines:rx1:0.1-SNAPSHOT"
}
```

See the [releases page](https://github.com/AkshayChordiya/kotlinx.coroutines-rx1/releases) for up to date release versions and details

## Usage

### To Flow and reverse 

| Name              	| Description                               	|
|-------------------	|-------------------------------------------	|
| Observable.asFlow 	| Transforms the given Observable into Flow 	|
| Flow.asObservable 	| Transforms the given Flow into Observable 	|

As expected the result type will receive all the events emitted from source cold stream.

### To Kotlin coroutines / suspend functions: 

| Name                           	| Description                                                                  	|
|--------------------------------	|------------------------------------------------------------------------------	|
| Completable.awaitCompleted    	| Awaits for completion of the completable value                               	|
| Single.await                   	| Awaits for completion of the single value and returns it                     	|
| Observable.awaitFirst          	| Returns the first value from the given observable                            	|
| Observable.awaitFirstOrDefault 	| Returns the first value from the given observable or default                 	|
| Observable.awaitFirstOrElse    	| Returns the first value from the given observable or default from a function 	|
| Observable.awaitFirstOrNull    	| Returns the first value from the given observable or null                    	|
| Observable.awaitFirst          	| Returns the last value from the given observable                             	|
| Observable.awaitSingle         	| Returns the single value from the given observable                           	|

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for contributions.

## License

    The MIT License (MIT)
    
    Copyright (c) 2020 Akshay Chordiya
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.