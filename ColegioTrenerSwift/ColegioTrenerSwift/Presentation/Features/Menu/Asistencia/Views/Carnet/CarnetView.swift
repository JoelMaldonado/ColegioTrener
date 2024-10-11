//
//  CarnetView.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/08/24.
//

import SwiftUI
import SwiftUIToast

struct CarnetView: View {
    @StateObject private var viewModel = CarnetViewModel()
    var body: some View {
        VStack {
            SelectHijo(
                hijoSelected: $viewModel.hijoSelected,
                listHijos: viewModel.listHijos,
                click: { ctacli in
                    Task {
                        await viewModel.getCarnet(ctacli: ctacli)
                    }
                }
            )
            
            if let urlImage = viewModel.carnetUrl {
                AsyncImage(url: urlImage) { res in
                    switch res {
                    case .empty:
                        ProgressView()
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFit()
                    case .failure(let error):
                        Text("No se pudo obtener la imagen")
                    @unknown default:
                        Text("Sin Imagen")
                    }
                    
                }
                .frame(maxWidth: .infinity)
                .padding()
            }
            
            if let urlDownload = viewModel.downloadUrl {
                Link(
                    destination: urlDownload,
                    label: {
                        Text("Descargar")
                            .foregroundStyle(.white)
                            .bold()
                            .padding(.horizontal)
                            .padding(.vertical, 4)
                            .background(.colorS1)
                            .clipShape(.capsule)
                    }
                )
            }
            
            Spacer()
            SUIToastViewContainer(stackOverlap: .stack)
        }
    }
}
