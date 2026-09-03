# Butterscotch Preprocessor

Butterscotch Preprocessor converts a GameMaker `data.win` file into binary
texture and audio assets for PlayStation 2 or PlayStation 3 targets.

## Requirements

- Java 21 or newer
- The included Gradle wrapper

## CLI usage

Run the CLI from the repository root:

```shell
./gradlew :processor-cli:run --args='/path/to/data.win'
```

Processed files are written to `output/` by default. To select a target and a
different output directory:

```shell
./gradlew :processor-cli:run --args='/path/to/data.win --target ps2 --output ./build/processed'
```

Paths containing spaces must be quoted inside `--args`:

```shell
./gradlew :processor-cli:run --args='"/path/to/My Game/data.win" --output "./My Output"'
```

### Options

```text
Usage: butterscotch-preprocessor [<options>] <datawinpath>

Arguments:
  <datawinpath>           Path to the data.win file

Options:
  -o, --output=<path>     Output directory (default: output)
  --debug-output=<path>   Dump sprites, backgrounds, fonts, tiles, and atlas
                          debug images to this directory (PS2 only)
  --force-4bpp=<regex>    Force matching image names to use at most 16 colors;
                          the regex must match the whole name and the option
                          may be repeated (PS2 only)
  --atlas-size=<pixels>   Width and height of each square texture atlas
                          (PS2 only)
  --target=(ps2|ps3)      Output platform (default: ps3)
  -h, --help              Show CLI help
```

For example, this PS2 command uses 1024-pixel atlases, forces sprite names
beginning with `spr_ui_` or `spr_font_` to 4bpp, and writes debug PNGs:

```shell
./gradlew :processor-cli:run --args='/path/to/data.win --target ps2 --atlas-size 1024 --force-4bpp "spr_ui_.*" --force-4bpp "spr_font_.*" --debug-output ./build/debug'
```

When processing for PS2, the CLI also discovers related audio beside
`data.win`:

- `.ogg` and `.wav` files in the same directory
- `audiogroup<N>.dat` files in the same directory, excluding group 0
- `.ogg` files recursively inside subdirectories such as `mus/`

## Build a launcher

Create a self-contained application distribution with its runtime
dependencies:

```shell
./gradlew :processor-cli:installDist
```

Run the generated launcher:

```shell
./processor-cli/build/install/processor-cli/bin/processor-cli \
  /path/to/data.win --target ps2 --output ./output
```

Archive distributions can be built with:

```shell
./gradlew :processor-cli:distZip
./gradlew :processor-cli:distTar
```

## Output files

The default PS3 target writes:

- `textures.bin`

The PS2 target writes:

- `CLUT4.BIN`
- `CLUT8.BIN`
- `TEXTURES.BIN`
- `ATLAS.BIN`
- `SOUNDBNK.BIN`
- `SOUNDS.BIN`

Supplying `--debug-output` for a PS2 run additionally creates extracted PNGs
under `sprites/`, `backgrounds/`, `fonts/`, `tiles/`, and `atlas_debug/`.