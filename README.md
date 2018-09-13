# react-native-facetec-zoom

this is a minimal wrapper around the [Zoom SDK](https://dev.zoomlogin.com/), that currently only runs the liveness detection check.

because the ZoomAuthenticationHybrid.framework file is > 100MB, you'll need `git lfs` set up first:

```sh
brew install git-lfs
git lfs install
```

then:

```sh
# download form app.tradle.io
curl https://s3.amazonaws.com/app.tradle.io/sdk/ZoomAuthenticationHybrid.framework-6.8.0.zip > ZoomAuthenticationHybrid.framework.zip
unzip ZoomAuthenticationHybrid.framework.zip -d ios/
rm ZoomAuthenticationHybrid.framework.zip

# or download from zoom directly: https://dev.zoomlogin.com/zoomsdk/#/ios-guide
# and put ZoomAuthenticationHybrid.framework in node_modules/react-native-facetec-zoom/ios/
```

```sh
npm i -S tradle/react-native-facetec-zoom
react-native link react-native-facetec-zoom
```
