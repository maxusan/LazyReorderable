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
![LazyReorderable-demo1](https://github.com/maxusan/LazyReorderable/assets/49410609/d24a3605-74d9-438f-88da-992c09028f94)

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
