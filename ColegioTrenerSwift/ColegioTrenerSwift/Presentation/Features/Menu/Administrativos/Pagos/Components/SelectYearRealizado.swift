//
//  SelectYearRealizado.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI


extension PagosView {
    
    @ViewBuilder
    func SelectYearRealizado() -> some View {
        
        HStack(spacing: 16) {
            Button {
                viewModel.year = getBeforeYear(date: viewModel.year)
                viewModel.getPagosRealizados()
            } label: {
                Text(getBeforeYear(date: viewModel.year).format(pattern: "yyyy"))
            }
            Text(viewModel.year.format(pattern: "yyyy"))
                .fontWeight(.bold)
            Button {
                viewModel.year = getAfterYear(date: viewModel.year)
                viewModel.getPagosRealizados()
            } label: {
                Text(getAfterYear(date: viewModel.year).format(pattern: "yyyy"))
            }
        }
        .font(.title3)
        .foregroundStyle(.white)
        .padding(.horizontal, 30)
        .padding(.vertical, 4)
        .background(
            LinearGradient(colors: [.white, .colorS1, .colorS1, .white], startPoint: .leading, endPoint: .trailing)
        )
    }
    
    
    func getAfterYear(date: Date) -> Date {
        return Calendar.current.date(byAdding: .year, value: 1, to: date) ?? date
        
    }
    
    func getBeforeYear(date: Date) -> Date {
        return Calendar.current.date(byAdding: .year, value: -1, to: date) ?? date
    }
    
}
