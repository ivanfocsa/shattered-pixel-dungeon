# Web (browser) version

The `web` module compiles the game to JavaScript with [TeaVM](https://teavm.org/) through
[gdx-teavm](https://github.com/xpenatan/gdx-teavm), so it runs in desktop and mobile browsers.

## Building

- `./gradlew web:gdx_teavm_web_js_run` builds a readable development version and serves it on http://localhost:8080
- `./gradlew web:gdx_teavm_web_js_release_build` builds the smaller release version into `web/build/dist/js/release/webapp`

The output folder is a static website: any static host works (GitHub Pages, itch.io, Netlify...).
The `.github/workflows/web.yml` workflow publishes it on GitHub Pages.

## How it differs from the other platforms

- Saves and settings are stored in the browser (IndexedDB and localStorage). Saves are not gzip-compressed on web.
- WebGL has no client-side vertex arrays, so `NoosaScript` streams vertices through buffers on web.
- Reflection: TeaVM only keeps what it is told to. `SPDReflectionSupplier` and `SPDTeaVMPlugin` (build-time only)
  make every game class loadable by name with its no-argument constructor, which is what `Bundle` needs.
- The UI scale uses the browser's device pixel ratio (`Game.density`).
