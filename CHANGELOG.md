# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

## [1.0.0](https://github.com/tomasanchez/disney-challenge/compare/v0.4.0...v1.0.0) (2022-01-04)


### Features

* add mail sender service ([88826c4](https://github.com/tomasanchez/disney-challenge/commit/88826c4d51c2d502c387dd7acde36d41e59fb25b))
* character full CRUD ([43b55f5](https://github.com/tomasanchez/disney-challenge/commit/43b55f5f361eb72266711a9b01fb68f953189439))
* support for default mail sender ([54a655b](https://github.com/tomasanchez/disney-challenge/commit/54a655bd3be68258e463d305901d2d20284eaf43))

## [0.4.0](https://github.com/tomasanchez/disney-challenge/compare/v0.3.0...v0.4.0) (2022-01-04)


### Features

* **api:** deep delete on Appearances ([d8bebcc](https://github.com/tomasanchez/disney-challenge/commit/d8bebcc03deaf4bda42edaa2fa8fdd86887e9e14))
* **api:** rest controller for appearances ([8a14913](https://github.com/tomasanchez/disney-challenge/commit/8a149134537f6abea3a86bb092b1a71dd3b31f61))
* **crud:** movies ([cf59bbb](https://github.com/tomasanchez/disney-challenge/commit/cf59bbb06cf7b0af363caa38dd7cf8c093fa9a8c))
* **crud:** post appearances with characters ([b734bbf](https://github.com/tomasanchez/disney-challenge/commit/b734bbfba60bce41e4ffb1211f7816c57c71ba7f))
* **crud:** searh params ([5548f39](https://github.com/tomasanchez/disney-challenge/commit/5548f39917bf0fbfed738ddaa837303ffa29a6eb))
* demo data ([c52b258](https://github.com/tomasanchez/disney-challenge/commit/c52b258619218ccd66df8d2efd7453a66690fd76))
* **model:** genres ([96c99da](https://github.com/tomasanchez/disney-challenge/commit/96c99da8194fad5f017dd832a17f23c115e4e972))


### Bug Fixes

* **appearance:** date and validators ([464f2d2](https://github.com/tomasanchez/disney-challenge/commit/464f2d23532bcdc27ddec64560e2e7bd764206fa))
* **crud:** deep delete ([34f47f2](https://github.com/tomasanchez/disney-challenge/commit/34f47f224f023466c9236e492e8b233411c3cce1))
* **genre filters:** false when genre is null ([62532ae](https://github.com/tomasanchez/disney-challenge/commit/62532aecba0f5068876366a53ec63d1ad3ace97a))
* **validation:** no longer breaks on nulls ([3f4bc1d](https://github.com/tomasanchez/disney-challenge/commit/3f4bc1d7eaee5959b07ce0ae5c7dad33334f0a06))

## [0.3.0](https://github.com/tomasanchez/disney-challenge/compare/v0.2.0...v0.3.0) (2022-01-03)


### Features

* **api:** CRUD for Characters ([bf0caae](https://github.com/tomasanchez/disney-challenge/commit/bf0caae6ca8f2f63399f22d3052f22e15d214333))
* **api:** exposed characters endpoint ([23585a4](https://github.com/tomasanchez/disney-challenge/commit/23585a4112adc907f6e9c353503230febbc294b1))
* **api:** query params ([b2e032a](https://github.com/tomasanchez/disney-challenge/commit/b2e032a2fd106367285a8c1fba88ce7e165b8d31))
* **rest:** base controller not found exception ([fe280cd](https://github.com/tomasanchez/disney-challenge/commit/fe280cd0ff43021467b4b31acfa955896ecd2ba3))


### Bug Fixes

* **crud:** delete on not found entity no longer OK status ([4930b65](https://github.com/tomasanchez/disney-challenge/commit/4930b65948283beda8ae69b422aa35da2a06ff3d))
* **design:** changed endpoint to put ([440bd18](https://github.com/tomasanchez/disney-challenge/commit/440bd18a508050e201f667b14bac25b353e04184))

## [0.2.0](https://github.com/tomasanchez/disney-challenge/compare/v0.1.0...v0.2.0) (2022-01-02)


### Features

* **auth:** add authentication token ([ba5d11d](https://github.com/tomasanchez/disney-challenge/commit/ba5d11db923e9cfd1b654fc8d1afb64118fb7c6e))
* **auth:** add simple user auth ([258e924](https://github.com/tomasanchez/disney-challenge/commit/258e924d51eddc884dc43343b23e4e3a6bd58f45))
* **auth:** user register ([da93890](https://github.com/tomasanchez/disney-challenge/commit/da93890713aef64a9b5f6ed1f1c608a06944d634))
* **config:** add security configs ([fc91851](https://github.com/tomasanchez/disney-challenge/commit/fc918511a29ad6f1164aae35f7135575a773054f))
* **controller:** user controller ([ebb9dcc](https://github.com/tomasanchez/disney-challenge/commit/ebb9dcc0657289d837f50d1e4f59ff52d8cf5afd))
* expose get methods for repository ([ed189a6](https://github.com/tomasanchez/disney-challenge/commit/ed189a6e7f847ef87dc536cb32ad90655f47a47c))
* **login:** token authentication ([6d8eb19](https://github.com/tomasanchez/disney-challenge/commit/6d8eb19a8e55eccadd03c6875c799d4751961f42))
* **repository:** add user repository ([4b1b3bb](https://github.com/tomasanchez/disney-challenge/commit/4b1b3bb338cc53248a12a97fe317f72ea0a74d8d))


### Bug Fixes

* password no longers forces lowercase ([3ee2a55](https://github.com/tomasanchez/disney-challenge/commit/3ee2a557e9c69a8cb7188e25dd975d2c6a23cd1d))
* remove Gson ([37e3759](https://github.com/tomasanchez/disney-challenge/commit/37e3759156da808056596c0314c257028229b84b))
* routing ([2972429](https://github.com/tomasanchez/disney-challenge/commit/2972429d7cb0ec4f1d42481c28fbd878bcf3cbe4))

## [0.1.0](https://github.com/tomasanchez/disney-challenge/compare/v0.0.1...v0.1.0) (2022-01-01)


### Features

* **core-db:** add persistent entity superclass ([49c6f6e](https://github.com/tomasanchez/disney-challenge/commit/49c6f6e7ce7db2a3f9626493a575def2f9e2e2dd))
* **exceptions:** consistent character ([5aaa265](https://github.com/tomasanchez/disney-challenge/commit/5aaa2650f52648e4d2a07b16eb6f791159b9cc11))
* **model:** add fictional character ([7d136a6](https://github.com/tomasanchez/disney-challenge/commit/7d136a68ec9e0ca2354120075f5133c4bfaee9fb))
* **model:** add movies & series ([909cb37](https://github.com/tomasanchez/disney-challenge/commit/909cb37a9e409e0510df4551100a4fad9e54cab2))
* **model:** many to many mapping ([7308bbb](https://github.com/tomasanchez/disney-challenge/commit/7308bbbd6d190c334d56f902425ba904a86c053b))
* **model:** persist genre ([ff808e0](https://github.com/tomasanchez/disney-challenge/commit/ff808e0e1c677397367915d1873958cef44ab40b))
* **model:** relationship mapping ([5fcd7d7](https://github.com/tomasanchez/disney-challenge/commit/5fcd7d732157155760c133031e826fc5cc8adf42))
* **repositories:** add character repository ([0f26350](https://github.com/tomasanchez/disney-challenge/commit/0f263508144142abe7c038ae003c9d3b328062da))


### Bug Fixes

* **toString:** changed Gson to Jackson ([01acffc](https://github.com/tomasanchez/disney-challenge/commit/01acffc52c36aeab3f13a319053c6f211fdc2503))

### 0.0.1 (2022-01-01)


### Features

* **env:** add standard version ([d4d618e](https://github.com/tomasanchez/disney-challenge/commit/d4d618e6024ec4bdb9bcd3f03e637a386a227ca6))
