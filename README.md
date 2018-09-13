# react-native-facetec-zoom

this is a minimal wrapper around the [Zoom SDK](https://dev.zoomlogin.com/), that currently only runs the liveness detection check.

because the ZoomAuthenticationHybrid.framework file is > 100MB, you'll need `git lfs` set up first:

```sh
brew install git-lfs
git lfs install
```

then:

```sh
npm i -S tradle/react-native-facetec-zoom
react-native link react-native-facetec-zoom
```
