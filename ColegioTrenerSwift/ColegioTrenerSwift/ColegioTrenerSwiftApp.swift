//
//  ColegioTrenerSwiftApp.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

@main
struct ColegioTrenerSwiftApp: App {
    @State private var isSplashActive: Bool = true
    @State var selectedDate: Date = Date()
    
    var body: some Scene {
        WindowGroup {
            ZStack {
                NavigationStack {
                    LoginView()
                }
                SplashView(isActive: $isSplashActive)
            }.onAppear {
                DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                    withAnimation {
                        self.isSplashActive = false
                    }
                }
            }
        }
    }
}
