package br.com.devsrsouza.svg2compose

import androidx.compose.material.icons.generator.toKotlinPropertyName
import org.junit.Test
import java.io.File

class TestMain {

    companion object {
        const val DEST_DIR = "src/test/kotlin"
        const val APPLICATION_PACKAGE = "br.com.compose.icons"
        const val PACKAGE_DIR = "br/com/compose/icons"
    }

    @Test
    fun traverseGeneratorTest() {
        val iconsDir = File("src/test/resources/icons")
        val destinationDir = File(DEST_DIR).apply { mkdirs() }

        assert(iconsDir.exists())
        assert(destinationDir.exists())

        Svg2Compose.parse(
            applicationIconPackage = APPLICATION_PACKAGE,
            accessorName = "Icons",
            outputSourceDirectory = destinationDir,
            vectorsDirectory = iconsDir,
            filenameFilter = { _, name -> name.startsWith("ic_") },
            iconNameTransformer = { name, _ ->
                name.removePrefix("ic_").toKotlinPropertyName().trim()
            },
            type = VectorType.DRAWABLE,
            allAssetsPropertyName = "Icons"
        )

        val generated = File(destinationDir, PACKAGE_DIR)
        assert(generated.exists())
    }
}