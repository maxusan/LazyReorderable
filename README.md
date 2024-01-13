# LazyReorderable
A Jetpack Compose library to provide Drag and Drop functionality for LazyColumns.

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
![JitPack](https://img.shields.io/jitpack/version/com.github.maxusan/LazyReorderable)


## Table of Contents

- [Demo](#demo)
- [Installation](#installation)
- [Usage](#usage)
## Demo

Demo of the sample reorderable list without footer item.
https://github.com/maxusan/LazyReorderable/assets/49410609/d1e6512c-2fec-402e-b307-5772f4946a6d

## Installation

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.maxusan:LazyReorderable:$current_library_version'
	} 

 ## Usage
 You can check [/app](/app) directory which includes example application for demonstration. 
