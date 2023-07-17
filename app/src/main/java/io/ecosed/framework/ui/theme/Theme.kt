/**
 * 此文件是Ecosed Framework的一部分。
 *
 */
package io.ecosed.framework.ui.theme

import android.content.Context
import android.os.Build
import android.view.View
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * 创建者: wyq0918dev
 * 源码仓库: https://github.com/Ecosed/EcosedFramework
 * 创建时间: 2023/07/17
 * 描述: Compose主题样式
 * 文档:
 */

/**
 * Compose主题样式
 *
 * @param dynamicColor 是否启用动态颜色
 * @param content 页面内容
 */
@Composable
fun EcosedFrameworkTheme(
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // 获取系统是否处于深色模式
    val darkTheme: Boolean = isSystemInDarkTheme()
    // 获取上下文
    val context: Context = LocalContext.current
    // 获取ComposeView
    val view: View = LocalView.current
    // 初始化系统栏控制器
    val systemUiController: SystemUiController = rememberSystemUiController()

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) {
                dynamicDarkColorScheme(
                    context = context
                )
            } else {
                dynamicLightColorScheme(
                    context = context
                )
            }
        }

        darkTheme -> darkColorScheme(
            primary = Purple80,
            secondary = PurpleGrey80,
            tertiary = Pink80
        )

        else -> lightColorScheme(
            primary = Purple40,
            secondary = PurpleGrey40,
            tertiary = Pink40
        )
    }

    if (!view.isInEditMode) SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !darkTheme
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}