package com.jjmf.colegiotrenerandroid.ui.features.Menu.Features.Administrativos.Pagos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjmf.colegiotrenerandroid.ui.theme.ColorS1
import com.jjmf.colegiotrenerandroid.util.format

@Composable
fun CardPagoRealizado(
    modifier: Modifier = Modifier,
    title: String,
    label: String,
    numDoc: String,
    medioPago: String,
    fecPago: String,
    importe: String,
    mora: String,
    fecVen: String
) {


    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ColorS1)
            ) {

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 4.dp),
                    text = label,
                    fontSize = 10.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = title,
                    modifier = Modifier.align(Alignment.Center),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = buildAnnotatedString {
                            append("Doc: $numDoc\n")
                            append("Grado/Lugar Pago: $medioPago\n")
                            append("Fecha movimiento: $fecPago")
                        },
                        fontSize = 12.sp,
                        lineHeight = 14.sp
                    )
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                modifier = Modifier.size(18.dp),
                                imageVector = Icons.Default.MonetizationOn,
                                contentDescription = null
                            )
                            Text(
                                text = buildAnnotatedString {
                                    append("Importe: $importe\n")
                                    append("Mora: $mora")
                                },
                                fontSize = 12.sp,
                                lineHeight = 14.sp
                            )
                        }
                        Text(
                            text = buildAnnotatedString {
                                append("Fecha penalidad: $fecVen")
                            },
                            fontSize = 12.sp,
                            lineHeight = 14.sp
                        )
                    }
                }
            }
        }
    }
}