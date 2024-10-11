//
//  SelectArchivo.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 19/08/24.
//

import SwiftUI

struct SelectArchivo: View {
    @State private var showingFilePicker = false
    @Binding var selectedFileURL: URL?
    
    var body: some View {
        HStack {
            
            if let fileURL = self.selectedFileURL {
                Text(fileURL.lastPathComponent)
                    .bold()
                    .frame(maxWidth: .infinity, alignment: Alignment.leading)
                Spacer()
                    .frame(width: 12)
                Button {
                    self.selectedFileURL = nil
                } label: {
                    Image(systemName: "xmark")
                        .foregroundStyle(.colorS1)
                }
            } else {
                Text("Archivo...")
                    .bold()
                    .frame(maxWidth: .infinity, alignment: Alignment.leading)
                
                Spacer()
                    .frame(width: 12)
                Button {
                    showingFilePicker = true
                } label: {
                    Text("Cargar")
                        .foregroundStyle(.white)
                        .bold()
                        .padding(.horizontal)
                        .padding(.vertical, 2)
                        .background(.colorS1)
                        .clipShape(.capsule)
                }
                .sheet(isPresented: $showingFilePicker) {
                    FilePicker { url in
                        selectedFileURL = url
                    }
                }
            }
        }
        .frame(height: 42)
        .padding(.horizontal, 8)
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(.colorT1, lineWidth: 1)
        )
        
    }
}
