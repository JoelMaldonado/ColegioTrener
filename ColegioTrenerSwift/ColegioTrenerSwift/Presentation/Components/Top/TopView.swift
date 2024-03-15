//
//  TopView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct TopView: View {
    @Environment(\.dismiss) private var dismiss
    var title : String
    var body: some View {
            VStack{
                HStack{
                    Button {
                        dismiss()
                    } label: {
                        Image(.logo3)
                            .resizable()
                            .scaledToFit()
                            .frame(width: 60)
                    }
                    VStack(alignment: .leading){
                        Text(title)
                            .font(.title2)
                        NavigationLink {
                            NotificacionesView()
                                .navigationBarBackButtonHidden()
                        } label: {
                            Image(systemName: "bell.fill")
                        }
                    }
                    Spacer()
                    VStack(alignment: .trailing){
                        Button {
                            
                        } label: {
                            VStack{
                                Image(systemName: "person")
                                Text("Familia")
                                    .font(.footnote)
                            }
                        }
                        Text("RINALDI TRELLES")
                            .font(.title3)
                    }
                }
                .padding()
                .foregroundStyle(.white)
                
                LinearGradient(
                    gradient: Gradient(colors: [.colorS1, .white, .colorP1, .colorT1]),
                    startPoint: .leading,
                    endPoint: .trailing
                )
                .frame(maxWidth: .infinity)
                .frame(height: 5)
                
            }
            .bold()
            .padding()
            .background(
                LinearGradient(
                    gradient: Gradient(colors: [.colorTop1, .colorTop2]),
                    startPoint: .leading,
                    endPoint: .trailing
                )
            )
    }
}
