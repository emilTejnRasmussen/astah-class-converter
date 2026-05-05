# Astah Class Converter

Astah Class Converter is a small JavaFX desktop app that converts Java classes into text that can be pasted into Astah class diagrams.

## Features

- Paste a Java class
- Convert it into Astah-ready text
- Copy class name
- Copy attributes
- Copy operations
- Option to hide private methods

## How to use

1. Open the app.
2. Paste a Java class into the input area.
3. Click **Convert**.
4. Copy the class name, attributes, or operations.
5. Paste them into Astah.

## Astah paste workflow

1. Copy the class name and paste it onto the Astah diagram.
2. Select the class.
3. Copy attributes and paste them into the attribute compartment.
4. Copy operations and paste them into the operation compartment.

Tip: If operations do not paste correctly in Astah, create one operation manually first, select it, and then paste.

## Download

Go to the **Releases** page and download the latest zip file.

Example:

```text
AstahClassConverter-v1.0.0.zip
```

After downloading:

1. Extract the zip file.
2. Open the extracted folder.
3. Double-click `AstahClassConverter.exe`.

Do not move the `.exe` out of the folder. It needs the `app` and `runtime` folders to run.

## Development

This project is built with:

- Java
- JavaFX
- jpackage
