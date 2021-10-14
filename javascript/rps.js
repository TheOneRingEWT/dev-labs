const rps = (p1, p2) => {
    if (p1 === 'Scissors' && p2 === 'Rock' ||
        p1 === 'Paper' && p2 === 'Scissors' ||
        p1 === 'Rock' && p2 === 'Paper')
        return 'P2 wins'

    if (p1 === p2)
        return 'Draw'

    return 'P1 wins'
}

module.exports = { rps }