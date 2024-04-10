//
//  ColegioTrenerSwiftApp.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI
import MijickCalendarView

@main
struct ColegioTrenerSwiftApp: App {
    @State private var isSplashActive: Bool = true
    @State var selectedDate: Date = Date()
  
    var body: some Scene {
        WindowGroup {
            ZStack {
                NavigationStack {
                    DatePicker("Select Date", selection: $selectedDate)
                                     .padding(.horizontal)
                                     .datePickerStyle(.graphical)
                    // In loadView or viewDidLoad
                    
                }
                
                SplashView(isActive: $isSplashActive)
            }
            .onAppear {
                DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                    withAnimation {
                        self.isSplashActive = false
                    }
                }
            }
        }
    }
}

#Preview {
    CalendarView()
}

struct CalendarView : View {
    @State private var selectedDate: Date? = nil
    @State private var selectedRange: MDateRange? = .init()
    
    var body: some View {
        
        MCalendarView(
            selectedDate: $selectedDate,
            selectedRange: $selectedRange,
            configBuilder: configureCalendar
        )
    }
}

extension CalendarView {
    func configureCalendar(_ config: CalendarConfig) -> CalendarConfig {
        config
    }
}

struct СustomDayView: DayView {
    let date: Date
    let isCurrentMonth: Bool
    let selectedDate: Binding<Date?>?
    let selectedRange: Binding<MDateRange?>?
}

extension СustomDayView {
    func createDayLabel() -> AnyView {
        ZStack {
            createBackgroundView()
            createDayLabelText()
        }
        .erased() // cast to AnyView
    }
}

private extension СustomDayView {
    func createBackgroundView() -> some View {
        RoundedRectangle(cornerRadius: 4)
            .fill(Color.orange)
    }
    
    func createDayLabelText() -> some View {
        Text(getStringFromDay(format: "d"))
            .font(.system(size: 17))
            .foregroundColor(.white)
    }
}
