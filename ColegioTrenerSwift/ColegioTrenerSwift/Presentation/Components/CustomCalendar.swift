//
//  CustomCalendar.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI


struct DateValue: Identifiable {
    var id = UUID().uuidString
    var day: Int
    var date: Date
}

struct CustomCalendar: View {
    @State var date: Date = .now
    @State var currentMonth = 0
    
    private var years: [Date] = {
        var years: [Date] = []
        let calendar = Calendar.current
        let currentDate = Date()
        
        for i in 0..<5 {
            if let newYear = calendar.date(byAdding: .day, value: -i, to: currentDate) {
                years.append(newYear)
            }
        }
        
        return years
    }()
    
    var body: some View {
        VStack {
            let days: [String] = ["Dom","Lun", "Mar", "Mie", "Jue", "Vie", "Sab"]
            HStack {
                
                Button {
                    withAnimation {
                        self.currentMonth -= 1
                    }
                } label: {
                    Image(systemName: "chevron.left")
                        .font(.title2)
                }
                
                Spacer()
                
                VStack(alignment: .leading, spacing: 10) {
                    Text(self.date.format(pattern: "LLLL"))
                        .fontWeight(.semibold)
                }
                
                Spacer()
                
                Button {
                    withAnimation {
                        self.currentMonth += 1
                    }
                } label: {
                    Image(systemName: "chevron.right")
                        .font(.title2)
                }
                
            }
            .padding()
            .background(.colorT1)
            .foregroundStyle(.black)
            
            HStack(spacing: 0) {
                ForEach(days, id: \.self) { day in
                    Text(day)
                        .font(.callout)
                        .fontWeight(.semibold)
                        .frame(maxWidth: .infinity)
                }
            }
            
            let columns = Array(repeating: GridItem(.flexible()), count: 7)
            
            LazyVGrid(columns: columns, spacing: 0) {
                ForEach(extractDate()) { value in
                    CardView(value: value)
                }
            }
            
        }
        .background(.white)
        .clipShape(.rect(cornerRadius: 16))
        .shadow(radius: 8)
        .padding()
        .onChange(of: currentMonth) { newValue in
            self.date = getCurrentMonth()
        }
        
    }
    
    @ViewBuilder
    func CardView(value: DateValue) -> some View {
        VStack(spacing: 4) {
            if value.day != -1 {
                Text("\(value.day)")
                    .font(.title3.bold())
                if let p = years.first(where: { $0.format() == value.date.format()} ) {
                    Image(systemName: "circle.fill")
                        .font(.caption2)
                        .foregroundStyle(.colorP1)
                }
            }
        }
        .frame(height: 50, alignment: .top)
    }
    
    func getCurrentMonth() -> Date {
        let calendar = Calendar.current
        guard let currentMonth = calendar.date(byAdding: .month, value: self.currentMonth, to: .now)
        else { return Date() }
        return currentMonth
    }
    
    
    func extractDate() -> [DateValue] {
        let calendar = Calendar.current
        let currentMonth = getCurrentMonth()
        var days = currentMonth.getAllDates().compactMap { date -> DateValue in
            let day = calendar.component(.day, from: date)
            return DateValue(day: day, date: date)
        }
        
        let firstWeekDay = calendar.component(.weekday, from: days.first?.date ?? .now)
        for _ in 0..<firstWeekDay - 1 {
            days.insert(DateValue(day: -1, date: .now), at: 0)
        }
        return days
    }
}

extension Date {
    func getAllDates() -> [Date] {
        let cal = Calendar.current
        let startDate = cal.date(from: Calendar.current.dateComponents([.year, .month], from: self))!
        var range = cal.range(of: .day, in: .month, for: startDate)!
        return range.compactMap { day -> Date in
            return cal.date(byAdding: .day, value: day - 1, to: startDate)!
        }
    }
}

#Preview {
    CustomCalendar()
}
